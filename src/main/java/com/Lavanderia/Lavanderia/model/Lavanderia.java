package com.Lavanderia.Lavanderia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Lavanderia")
@Data
public class Lavanderia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private Integer prendaId;

    @Column(nullable = false)
    private Integer cantidadEnviada;

    private Integer cantidadRecibida;

    private String estadoRopa;

    private String area;
    
    private LocalDateTime fechaDeSalida;

    private LocalDateTime fechaDeRetorno;

    @PrePersist
    protected void onCreate() {
        fechaDeSalida = LocalDateTime.now();
        if (estadoRopa == null) estadoRopa = "PROCESANDO";
    }
    
}
