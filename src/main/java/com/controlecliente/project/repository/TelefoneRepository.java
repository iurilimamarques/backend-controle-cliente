/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.repository;

import com.controlecliente.project.models.Telefone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author iuri
 */
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    Telefone findById(long id);
    
    @Query(value = "SELECT * FROM TELEFONE s WHERE s.id_cliente = ?1", 
      nativeQuery = true)
    List<Telefone> getByCliente(int id_cliente);
}
