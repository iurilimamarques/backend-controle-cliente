/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.resources;

import com.controlecliente.project.DTO.EnderecoRequest;
import com.controlecliente.project.models.Cliente;
import com.controlecliente.project.models.Endereco;
import com.controlecliente.project.repository.ClienteRepository;
import com.controlecliente.project.repository.EnderecoRepository;
import io.swagger.annotations.Api;
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
public class EnderecoResource {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @PostMapping("/endereco/criar/{id_cliente}")
    public HashMap<String, String> addEndereco(@PathVariable(value="id_cliente") Integer id_cliente, @RequestBody EnderecoRequest enderecoRequest) {
        
        Cliente cliente = clienteRepository.findById(id_cliente);
        
        if(enderecoRequest.getStatus().equals("P")) {
            Endereco enderecoPrincipal = enderecoRepository.getPrincipalByCliente(id_cliente);
            if(enderecoPrincipal!=null){
                enderecoPrincipal.setStatus("N");
                enderecoRepository.save(enderecoPrincipal);
                enderecoRequest.setStatus("P");
            }
        }
        
        Endereco endereco = new Endereco(enderecoRequest.getRua(), enderecoRequest.getNumero(), enderecoRequest.getBairro(), enderecoRequest.getCidade(), enderecoRequest.getPais(), enderecoRequest.getStatus(), enderecoRequest.getEstado(), cliente);
        
        Endereco enderecoSalvo = enderecoRepository.saveAndFlush(endereco);
        
        HashMap<String, String> response = new HashMap<>();

        if(enderecoSalvo!=null){
            response.put("response", "endereco_salvo");
        }else{
            response.put("response", "nao_salvo");
        }
        
        return response;
    }
    
    @GetMapping("/endereco/desativar/{id_endereco}/{id_cliente}")
    public HashMap<String, List<Endereco>> deletaEndereco(@PathVariable(value="id_endereco") Integer id_endereco, @PathVariable(value="id_cliente") Integer id_cliente) {
        
        Endereco endereco = enderecoRepository.findById(id_endereco);
        endereco.setStatus("D");
        
        Endereco enderecoAtualizado = enderecoRepository.saveAndFlush(endereco);
        
        HashMap<String, List<Endereco>> response = new HashMap<>();
        List<Endereco> enderecos = enderecoRepository.getByCliente(id_cliente);

        if (enderecoAtualizado!=null) {
            response.put("response", enderecos);
        }else{
            response.put("response", enderecos);
        }
        
        return response;
    }
}
