/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.repository;

import com.controlecliente.project.models.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author iuri
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findById(long id);
    
    @Query(value = "SELECT * FROM ATIVIDADE s WHERE s.id_usuario = ?1", 
			  nativeQuery = true)
			List<Cliente> getClientesByUsuario(int id_usuario);
}
