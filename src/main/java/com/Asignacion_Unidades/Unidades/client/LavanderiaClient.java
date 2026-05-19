package com.Asignacion_Unidades.Unidades.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Asignacion_Unidades.Unidades.dto.LavanderiaDTO;

@FeignClient(name = "lavanderia-service", url = "http://localhost:8082/api/lavanderia")
public interface LavanderiaClient {
    @GetMapping("/{id}")
    LavanderiaDTO consultarEstado(@PathVariable("id") Integer id);

    @PostMapping("/registrar")
    LavanderiaDTO solicitarServicio(@RequestBody LavanderiaDTO solicitud, @RequestParam("cantidad") Integer cantidad);
}
