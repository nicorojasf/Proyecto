package com.Usuarios.Usuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Usuarios.Usuarios.client.UsuarioClient;
import com.Usuarios.Usuarios.model.Usuario;
import com.Usuarios.Usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
    

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioClient usuarioClient;

public Usuario registrarUsuario(Usuario usuario) {
    if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) { 
        throw new RuntimeException("El nombre de usuario ya está registrado en la clínica.");
    }
    
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    
    if (usuario.getRol() != null && !usuario.getRol().startsWith("ROLE_")) {
        usuario.setRol("ROLE_" + usuario.getRol());
    }
    
    return usuarioRepository.save(usuario);
}

public String verificarLogin(String username, String password) {
    Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario o contraseña no válidos."));

    
    if (!passwordEncoder.matches(password, usuario.getPassword())) {
        throw new RuntimeException("Usuario o contraseña no válidos.");
    }

    return "Autenticación exitosa en el sistema de Ropería ";
    }

    public List<Object> consultarPrendasDisponibles() {
        ResponseEntity<List<Object>> respuesta = usuarioClient.listarTodasLasPrendas();
        return respuesta.getBody();
    }
}