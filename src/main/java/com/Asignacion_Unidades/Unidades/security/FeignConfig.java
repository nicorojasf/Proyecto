package com.Asignacion_Unidades.Unidades.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class FeignConfig {
    
    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Desactivamos CSRF
        .authorizeHttpRequests(auth -> auth
            // Permitimos que cualquier petición que llegue a este puerto se procese directamente
            .anyRequest().permitAll() 
        );
        
    return http.build();
}
}