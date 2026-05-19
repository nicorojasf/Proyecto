package com.Usuarios.Usuarios.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import feign.auth.BasicAuthRequestInterceptor;

@FeignClient(name = "microservicio-inventario", url = "http://localhost:8081/api/prendas", configuration = UsuarioClient.Configuration.class)
public interface UsuarioClient {


    @GetMapping("/listar")
    ResponseEntity<List<Object>> listarTodasLasPrendas();
    
    class Configuration {
        @Bean
        public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
            return new BasicAuthRequestInterceptor("admin", "admin123");
        }
}
}