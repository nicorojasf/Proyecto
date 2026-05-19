package com.bupa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bupa.dto.LavanderiaDTO;

@FeignClient(name = "Lavanderia", url = "http:localhost:8082/api/lavanderia")
public interface LavanderiaClient {
    @GetMapping("/procesos/{id}")
    ResponseEntity<LavanderiaDTO> obtenerProcesoLavado(@PathVariable("id") Integer id);
}
