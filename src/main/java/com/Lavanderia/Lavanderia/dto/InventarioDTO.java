package com.Lavanderia.Lavanderia.dto;

import lombok.Data;

@Data
public class InventarioDTO {
    private Integer id;
    private String nombrePrenda;
    private String material;
    private String categoria;
    private Integer stockActual;
    private Integer stockBodega;
    private Integer stockMinimo;
}   
