package com.bupa.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BAJAS_PRENDAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BajaPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "prenda_id", nullable = false)
    private Integer prendaId; //Conexion INVENTARIO (8081)

    @Column(nullable = false)
    private Integer cantidad; //Conexion MOTIVODESCARTE

    @Column(nullable = false)
    private String motivo; //ROTURA_LAVADO, DESGASTE, EXTRAVIO

    private String moduloOrigen; //LAVANDERIA, UNIDADES
    private String auditorResponsable; //nombre o admin que lo ejecuta
    private String informeTecnico; //Destalle de descarte
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate(){
        this.fechaRegistro = LocalDateTime.now();
    }
}
