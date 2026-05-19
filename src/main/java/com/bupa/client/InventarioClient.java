package com.bupa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bupa.dto.InventarioDTO;

@FeignClient(name = "inventario-service", url = "http://localhost:8081/api/prendas")
public interface InventarioClient {

    @GetMapping("/{id}")
    ResponseEntity<InventarioDTO> obtenerPorId(@PathVariable("id") Integer id);

    @PutMapping("/{id}/descontar")
    ResponseEntity<String> descontarStock(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad);
}