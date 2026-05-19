package com.bupa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bupa.model.Alerta;
import com.bupa.service.AlertaService;

@RestController
@RequestMapping("/api/gestion")
public class GestionTextilContoller {
    
    @Autowired
    private AlertaService alertaService;

    //Endpoint unificado para recibir las excepciones automaticas
    @PostMapping("/alertas/disparar")
    public ResponseEntity<List<Alerta>> emitirNotificacion(@RequestBody Alerta alerta){
        return ResponseEntity.ok(alertaService.listarAlertasActivas());
    }

    @GetMapping("/alertas/origen/{modulo}")
    public ResponseEntity<List<Alerta>> filtrarPorOrigen(@PathVariable String modulo) {
        return ResponseEntity.ok(alertaService.listarAlertasActivasPorModulo(modulo));
    }

    @PutMapping("/alertas/{id}/resolver")
    public ResponseEntity<Alerta> resolverIncidente(@PathVariable Integer id) {
        return ResponseEntity.ok(alertaService.cerrarAlerta(id));
    }
}
