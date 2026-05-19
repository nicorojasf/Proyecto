package com.Inventario.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Inventario.Inventario.model.Prendas;
import com.Inventario.Inventario.service.PrendasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("api/prendas")
public class PrendasController {

    @Autowired
    private PrendasService prendasService;

    @GetMapping("/{id}")
    public ResponseEntity<Prendas> obtenerPorId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(prendasService.obtenerPorId(id));
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Prendas>> listarTodo() {
    return ResponseEntity.ok(prendasService.obtenerTodas());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Prendas> guardar(@RequestBody Prendas prendas) {
    return ResponseEntity.ok(prendasService.guardar(prendas));
    }

    @PutMapping("/{id}/descontar")
    public ResponseEntity<String> descontar(@PathVariable Integer id, @RequestParam Integer cantidad) {
    prendasService.descontarStock(id, cantidad);
    return ResponseEntity.ok("Stock descontado exitosamente en Inventario");
    }

    @PutMapping("/{id}/aumentar")
    public ResponseEntity<String> aumentar(@PathVariable Integer id, @RequestParam Integer cantidad) {
    prendasService.aumentarStock(id, cantidad);
    return ResponseEntity.ok("Stock aumentado exitosamente en Inventario");
}
}
