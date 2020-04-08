/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.models;

import com.controlecliente.project.models.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author iuri
 */
@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String nome;

    private String senha;

    private String email;
    
    @Transient
    private String access_token;
    
    @Transient
    private String token_type;
    
    @Transient
    private String refresh_token;

    @Transient
    private String expires_in;
    
    public Usuario(String nome, String senha, String email,
                     List<Cliente> cliente, String access_token, String token_type, String refresh_token, String expires_in) {
            super();
            this.nome = nome;
            this.senha = senha;
            this.email = email;
            this.cliente = cliente;
            this.access_token = access_token;            
            this.token_type = token_type;
            this.refresh_token = refresh_token;            
            this.refresh_token = expires_in;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(String expires_in) {
        this.expires_in = expires_in;
    }
    
    public Usuario() {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getToken() {
        return access_token;
    }

    public void setToken(String access_token) {
        this.access_token = access_token;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(fetch = FetchType.LAZY)
    private List<Cliente> cliente;	
        
}
