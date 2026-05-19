package com.Usuarios.Usuarios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Usuarios.Usuarios.model.Usuario;
import com.Usuarios.Usuarios.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    // Ruta pública para registrar empleados
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    // Ruta pública para verificar credenciales
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credenciales) {
        String respuesta = usuarioService.verificarLogin(credenciales.get("username"), credenciales.get("password"));
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/ver-prendas")
    public ResponseEntity<List<Object>> verPrendasDesdeUsuarios() {
        return ResponseEntity.ok(usuarioService.consultarPrendasDisponibles());
    }
}
