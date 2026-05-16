package com.Asignacion_Unidades.Unidades.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ASIGNACION_UNIDADES")
@Data
public class AsignacionUnidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private Integer idUnidad;

    private Integer stockUnidad;

    private String areaAsignada;

    private String estadoUnidad; // "ASIGNADA", "EN LAVANDERIA", "DISPONIBLE"

    private LocalDateTime fechaAsignacion;

    private LocalDateTime fechaDevolucion;

    @PrePersist
    protected void onCreate() {
        this.fechaAsignacion = LocalDateTime.now();
        if (this.estadoUnidad == null) this.estadoUnidad = "ASIGNADA";
    }
}


    


