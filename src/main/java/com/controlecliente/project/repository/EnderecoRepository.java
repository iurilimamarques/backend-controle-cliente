/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.repository;

import com.controlecliente.project.models.Cliente;
import com.controlecliente.project.models.Endereco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author iuri
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Endereco findById(long id);
    
    @Query(value = "SELECT * FROM ENDERECO s WHERE s.id_cliente = ?1 AND s.status='P'", 
			  nativeQuery = true)
			Endereco getPrincipalByCliente(int id_cliente);
                        
    @Query(value = "SELECT * FROM ENDERECO s WHERE s.id_cliente = ?1 AND status!='D'", 
      nativeQuery = true)
    List<Endereco> getByCliente(int id_cliente);
}
