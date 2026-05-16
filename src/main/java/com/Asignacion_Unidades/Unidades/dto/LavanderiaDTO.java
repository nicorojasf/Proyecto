package com.Asignacion_Unidades.Unidades.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LavanderiaDTO {
    private Integer id; // El ID del registro de lavandería
    private Integer prendaId; // El ID de la prenda (ej: 47)
    private Integer cantidadEnviada;
    private Integer cantidadRecibida;
    private String estadoRopa; // "PROCESANDO" o "FINALIZADO"
    private String area; // Área a la que se asignará la ropa una vez finalizada
    private LocalDateTime fechaDeSalida;
    private LocalDateTime fechaDeRetorno;
}