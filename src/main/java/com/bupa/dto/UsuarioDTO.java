package com.bupa.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String username;
    private String mail;
    private boolean activo;
}
