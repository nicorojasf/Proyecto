package com.bupa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bupa.dto.UsuarioDTO;

@FeignClient(name = "usuarios-service", url = "http://localhost:8084/api/usuarios")
public interface UsuarioClient {
    @GetMapping("/buscar/{username}")
    ResponseEntity<UsuarioDTO> obtenerPorUsername(@PathVariable("username") String username);
}
