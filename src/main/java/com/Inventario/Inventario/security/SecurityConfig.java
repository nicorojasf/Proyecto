package com.Inventario.Inventario.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // Desactivar CSRF para que Postman pueda enviar POST/PUT
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/lavanderia/**").permitAll() // Permitir todo lo de Lavandería
            .requestMatchers("/api/prendas/**").permitAll()    // IMPORTANTE: Permitir también Inventario
            .anyRequest().permitAll() // Por ahora, permite TODO para que no te bloquee nada
        );
    return http.build();
    }
}
