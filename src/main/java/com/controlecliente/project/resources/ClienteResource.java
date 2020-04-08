/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.resources;

import com.controlecliente.project.DTO.ClienteRequest;
import com.controlecliente.project.models.Cliente;
import com.controlecliente.project.models.Endereco;
import com.controlecliente.project.models.Telefone;
import com.controlecliente.project.models.Usuario;
import com.controlecliente.project.repository.ClienteRepository;
import com.controlecliente.project.repository.EnderecoRepository;
import com.controlecliente.project.repository.TelefoneRepository;
import com.controlecliente.project.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iuri
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST")
public class ClienteResource {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private TelefoneRepository telefoneRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @GetMapping("/cliente/{id_usuario}")
    public HashMap<String, List<Cliente>> addUsuario(@PathVariable(value="id_usuario") Integer id_usuario) {
        
        List<Cliente> clientes = clienteRepository.getClientesByUsuario(id_usuario);
        
        HashMap<String, List<Cliente>> response = new HashMap<>();
        
        response.put("response", clientes);
        
        return response;
    }
    
    @GetMapping("/cliente/dados/{id_cliente}")
    public HashMap<String, HashMap<String, ?>> getDados(@PathVariable(value="id_cliente") Integer id_cliente) {
        
        List<Endereco> ender = enderecoRepository.getByCliente(id_cliente);        
        List<Telefone> telef = telefoneRepository.getByCliente(id_cliente);
        
        HashMap<String, List<?>> dados = new HashMap<>();
        
        dados.put("enderecos", ender);        
        dados.put("telefones", telef);
        
        HashMap<String, HashMap<String, ?>> response = new HashMap<>();
        
        response.put("response", dados);        
        
        return response;
    }
    
    @PostMapping("/cliente/criar/{id_usuario}")
    public HashMap<String, String> addCliente(@PathVariable(value="id_usuario") Integer id_usuario, @RequestBody ClienteRequest clienteRequest) {
        
        List<Cliente> retorno = clienteRepository.getClienteByCpf(id_usuario, clienteRequest.getCpfCnpj());
        
        HashMap<String, String> response = new HashMap<>();

        if(retorno.size()==0){
            Usuario usuario = usuarioRepository.findById(id_usuario);

            Cliente cliente = new Cliente(clienteRequest.getNome(), clienteRequest.getCpfCnpj(), "A", usuario);

            Cliente clienteAdicionado = clienteRepository.saveAndFlush(cliente);

            List<Telefone> telefones = new ArrayList<>();

            for(Telefone tel : clienteRequest.getTelefone()) {
                tel.setCliente(clienteAdicionado);
                telefones.add(tel);
            }

            telefoneRepository.saveAll(telefones);
            
            response.put("response", "cliente_salvo");
        }else{
            response.put("response", "cliente_existente");
        }
        
        return response;
    }
}
