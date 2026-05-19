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

    // Acción propia: Registrar usuarios de la clínica encriptando la clave
    // Acción propia: Registrar usuarios de la clínica encriptando la clave
public Usuario registrarUsuario(Usuario usuario) {
    // CORRECCIÓN 1: Agregamos .isPresent() para que devuelva un valor boolean (true/false)
    if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) { 
        throw new RuntimeException("El nombre de usuario ya está registrado en la clínica.");
    }
    
    // Encriptamos la contraseña antes de mandarla a Oracle Cloud
    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    
    // Aseguramos el prefijo de Spring Security para los roles
    if (usuario.getRol() != null && !usuario.getRol().startsWith("ROLE_")) {
        usuario.setRol("ROLE_" + usuario.getRol());
    }
    
    return usuarioRepository.save(usuario);
}

// Acción propia: Validar credenciales
public String verificarLogin(String username, String password) {
    // CORRECCIÓN 2: Buscamos el Optional y aplicamos correctamente el .orElseThrow() en una sola línea
    Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario o contraseña no válidos."));

    // Comparamos la contraseña en texto plano con la encriptada de la base de datos
    if (!passwordEncoder.matches(password, usuario.getPassword())) {
        throw new RuntimeException("Usuario o contraseña no válidos.");
    }

    return "Autenticación exitosa en el sistema de Ropería ";
    }

    // Acción de COMUNICACIÓN: Llama a tu microservicio de Inventario a través de Feign
    public List<Object> consultarPrendasDisponibles() {
        ResponseEntity<List<Object>> respuesta = usuarioClient.listarTodasLasPrendas();
        return respuesta.getBody();
    }
}