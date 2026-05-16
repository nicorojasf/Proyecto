package com.Asignacion_Unidades.Unidades.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Asignacion_Unidades.Unidades.dto.InventarioDTO;

@FeignClient(name = "inventario-service", url = "http://localhost:8081/api/prendas")
public interface InventarioClient {

    @GetMapping("/{id}")
    InventarioDTO obtenerPrendaPorId(@PathVariable("id") Integer id);
    
    @PutMapping("/{id}/descontar")
    void llamarDescuento(@PathVariable("id") Integer id, @RequestParam("cantidad") Integer cantidad);
    
}
