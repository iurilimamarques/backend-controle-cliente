/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlecliente.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author iuri
 */
@Configuration
@EnableWebMvc
public class CorsConfig {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            public void addCorsMapping(CorsRegistry registry) {
                //registry.addMapping("/**").allowedOrigins("http://localhost:9000");
                /*
                registry.addMapping("/api/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedOrigins("*")
                        .exposedHeaders("*")
                        .allowCredentials(false).maxAge(3600);
                 
                registry.addMapping("/**")
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")                    
                        .allowedHeaders("*")                    
                        .allowedOrigins("http://localhost:9000");*/
            }
        };
            
    }
    
    
}