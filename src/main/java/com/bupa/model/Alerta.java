package com.bupa.model;

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
@Table(name = "ALERTAS_SISTEMA")
@Data
public class Alerta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String moduloEmisor; //INVENTARIO, LAVANDERIA, USUARIO, AUDITORIA, ASIGNACION

    @Column(nullable = false)
    private String tipoAlerta; //STOCK_CRITICO, RETRASO, ACCESO_INVALIDO, DESCARTE_MASIVO

    @Column(length = 1000, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String nivelCritico; //ALTA, MEDIA, INFORMATIVA

    //Relacion cruzada
    private Integer entidadReferenciaId; //guarda ID de Prenda, Lavado o Turno
    private String operadorResponsable; // USERNAME del responsable
    private boolean resuelta;
    private LocalDateTime fechaIncidente;

    @PrePersist
    protected void onCreate() {
        this.fechaIncidente = LocalDateTime.now();
        this.resuelta = false;
    }
}
