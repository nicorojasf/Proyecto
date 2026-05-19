package com.bupa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bupa.model.BajaPrenda;
import com.bupa.service.AuditoriaService;

@RestController
@RequestMapping("api/auditoria")
public class AuditoriaController {
    
    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<BajaPrenda>> obtenerHistorial() {
        return ResponseEntity.ok(auditoriaService.listarTodas());
    }

    @PostMapping("/descarte")
    public ResponseEntity<BajaPrenda> registrarDescarte(@RequestBody BajaPrenda descarte) {
        return ResponseEntity.ok(auditoriaService.procesarDescarte(descarte));
    }
}
