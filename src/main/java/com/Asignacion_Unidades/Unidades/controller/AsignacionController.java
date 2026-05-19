package com.Asignacion_Unidades.Unidades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Asignacion_Unidades.Unidades.dto.LavanderiaDTO;
import com.Asignacion_Unidades.Unidades.model.AsignacionUnidad;
import com.Asignacion_Unidades.Unidades.service.AsignacionService;

@RestController
@RequestMapping("/api/unidad")
public class AsignacionController {

    @Autowired
    private AsignacionService asignacionService;
    @GetMapping("/listar")
    public List<AsignacionUnidad> obtenerTodas() {
        return asignacionService.obtenerTodas();
    }

    @PostMapping("/asignar")
    public ResponseEntity<AsignacionUnidad> asignarRopa(
            @RequestParam Integer prendaId, 
            @RequestParam Integer cantidad, 
            @RequestParam String area) {
        
        return ResponseEntity.ok(asignacionService.realizarAsignacionInicial( prendaId, cantidad, area));
    }

    @PostMapping("/lavar")
    public ResponseEntity<LavanderiaDTO> mandarALavanderia(
            @RequestParam Integer prendaId, 
            @RequestParam Integer cantidad,
            @RequestParam String area) {
        
        return ResponseEntity.ok(asignacionService.enviarRopaSucia(prendaId, cantidad, area));
    }

  
}
