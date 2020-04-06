/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.repository;

import com.controlecliente.project.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author iuri
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findById(long id);
    
    @Query(value = "SELECT id,"
                        + "nome,"
                        + "email,"
                        + "senha FROM USUARIO s WHERE s.email = ?1 AND s.senha = ?2", 
			  nativeQuery = true)
                          Usuario verificaDadosUsuario(String email, String senha);

    @Query(value = "SELECT id,"
                        + "nome,"
                        + "email,"
                        + "senha FROM USUARIO s WHERE s.email = ?1", 
			  nativeQuery = true)
                          Usuario getUsuarioByEmail(String email);
}

