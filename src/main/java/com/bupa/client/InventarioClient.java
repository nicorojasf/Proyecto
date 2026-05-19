package com.bupa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bupa.dto.InventarioDTO;

@FeignClient(name = "inventario", url = "http://localhost:8081/api/prendas")
public interface InventarioClient {
    @GetMapping("/{id}")
    ResponseEntity<InventarioDTO> obtenerPrendaPorId(@PathVariable("id") Integer id);
}