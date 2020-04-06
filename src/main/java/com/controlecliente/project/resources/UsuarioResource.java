/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.resources;

import com.controlecliente.project.models.Usuario;
import com.controlecliente.project.repository.UsuarioRepository;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

/**
 *
 * @author iuri
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST")
public class UsuarioResource {
    
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioResource(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
       this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    
    @PostMapping("/usuario/criar")
    public HashMap<String, String> addUsuario(@RequestBody Usuario usuario) {
        
        HashMap<String, String> response = new HashMap<>();
        
        if (usuarioRepository.getUsuarioByEmail(usuario.getEmail())==null) {
            usuarioRepository.save(usuario);
        
            response.put("response", "usuario_criado");
        }else{
            response.put("response", "usuario_existente");
        }
        
        return response;
    }
    
    
    @PostMapping("/usuario")
    public HashMap<String, Usuario> loginUsuario(@RequestBody Usuario usuario, @RequestHeader("Authorization") String authorization) {
        
        Usuario usuarioRetorno = usuarioRepository.verificaDadosUsuario(usuario.getEmail(), usuario.getSenha());
        
        HashMap<String, Usuario> response = new HashMap<>();

        if (usuarioRetorno!=null) {
            if (inMemoryUserDetailsManager.userExists(usuario.getEmail()) ) {
                inMemoryUserDetailsManager.deleteUser(usuario.getEmail());
            }
            
            inMemoryUserDetailsManager.createUser(User.withUsername(usuario.getEmail()).password(usuario.getSenha()).roles("USER").build());
            
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();

            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", authorization); 

            MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
            body.add("grant_type", "password");            
            body.add("username", usuario.getEmail());            
            body.add("password", usuario.getSenha());
            
            String access_token_url = "http://localhost:8090/oauth/token";

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);

            ResponseEntity<String> resp = restTemplate.postForEntity( access_token_url, request , String.class );
            
            
            Gson gson = new Gson();
            
            Usuario usuarioJson = gson.fromJson(resp.getBody(), Usuario.class);
            usuarioRetorno.setToken(usuarioJson.getToken());            
            usuarioRetorno.setTokenType(usuarioJson.getTokenType());            
            usuarioRetorno.setRefreshToken(usuarioJson.getRefreshToken());
            usuarioRetorno.setExpiresIn(usuarioJson.getExpiresIn());
            
            response.put("response", usuarioRetorno);
        }else{
            response.put("response", usuarioRetorno);
        }
        
        return response;
    }
}