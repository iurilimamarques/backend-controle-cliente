/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.repository;

import com.controlecliente.project.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author iuri
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findById(long id);

}
