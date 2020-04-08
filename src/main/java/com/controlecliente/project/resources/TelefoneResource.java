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
import com.controlecliente.project.repository.TelefoneRepository;
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
public class TelefoneResource {
    
    @Autowired
    private TelefoneRepository telefoneRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @PostMapping("/telefone/criar/{id_cliente}")
    public HashMap<String, List<Telefone>> addTelefone(@PathVariable(value="id_cliente") Integer id_cliente, @RequestBody Telefone telefone) {
        
        Cliente cliente = clienteRepository.findById(id_cliente);
        telefone.setCliente(cliente);
        
        Telefone telefoneAdicionado = telefoneRepository.saveAndFlush(telefone);
        
        HashMap<String, List<Telefone>> response = new HashMap<>();
        
        List<Telefone> telefones = telefoneRepository.getByCliente(id_cliente);

        if (telefoneAdicionado!=null) {
            response.put("response", telefones);
        }else{
            response.put("response", telefones);
        }
        
        return response;
    }
    
    @GetMapping("/telefone/remove/{id_numero}/{id_cliente}")
    public HashMap<String, List<Telefone>> getDados(@PathVariable(value="id_numero") Integer id_numero, @PathVariable(value="id_cliente") Integer id_cliente) {
        
        Telefone telefone = telefoneRepository.findById(id_numero);
        telefoneRepository.delete(telefone);
        
        List<Telefone> telefones = telefoneRepository.getByCliente(id_cliente);
        
        HashMap<String, List<Telefone>> response = new HashMap<>();
        
        response.put("response", telefones);        
        
        return response;
    }
    
}
