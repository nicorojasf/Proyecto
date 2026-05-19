package com.Lavanderia.Lavanderia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Lavanderia.Lavanderia.model.Lavanderia;
import com.Lavanderia.Lavanderia.service.LavanderiaService;


@RestController
@RequestMapping("/api/lavanderia")
public class LavanderiaController {
    @Autowired
    private LavanderiaService lavanderiaService;


    @GetMapping("/listar")
    public List<Lavanderia> listar() {
        return lavanderiaService.obtenerTodos();
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<Lavanderia> guardar(@RequestBody Lavanderia registro, @RequestParam Integer cantidad) {
    // Llamamos al service que ahora tiene la conexión Feign
    return ResponseEntity.ok(lavanderiaService.registrarEnvio(registro, cantidad));
    }
    
    @PutMapping("/retorno/{id}")
    public ResponseEntity<Lavanderia> registrarRetorno(
        @PathVariable Integer id, 
        @RequestParam Integer cantidad) {
    Lavanderia actualizado = lavanderiaService.registrarRetorno(id, cantidad);
    return ResponseEntity.ok(actualizado);
}
}
