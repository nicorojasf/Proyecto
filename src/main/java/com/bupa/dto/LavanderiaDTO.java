package com.bupa.dto;

import lombok.Data;

@Data
public class LavanderiaDTO {
    private Integer id;
    private String estado; // LAVANDO, RETRASO
    private String descripcion;
    private String maquinaId;
}
