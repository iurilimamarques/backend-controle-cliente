/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.resources;

import com.controlecliente.project.models.Cliente;
import com.controlecliente.project.models.Usuario;
import com.controlecliente.project.repository.ClienteRepository;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/cliente/{id_usuario}")
    public HashMap<String, List<Cliente>> addUsuario(@PathVariable(value="id_usuario") Integer id_usuario) {
        
        List<Cliente> clientes = clienteRepository.getClientesByUsuario(id_usuario);
        
        HashMap<String, List<Cliente>> response = new HashMap<>();
        
        response.put("response", clientes);
        
        return response;
    }
}
