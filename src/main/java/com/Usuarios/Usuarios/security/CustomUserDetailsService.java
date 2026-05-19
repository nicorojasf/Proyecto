package com.Usuarios.Usuarios.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Usuarios.Usuarios.model.Usuario;
import com.Usuarios.Usuarios.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Constructor para inyectar el repositorio que habla con Oracle Cloud
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos al usuario nico en la BD de Oracle Cloud
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado en la BD: " + username));

        // 2. Le retornamos el usuario con sus credenciales reales a Spring Security
        // Como guardaste "ROLE_ADMIN" en la BD, se pasa directo en .authorities()
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Trae el hash de BCrypt ($2a$10...)
                .authorities(usuario.getRol())   // Aquí lee "ROLE_ADMIN"
                .build();
    }
    
}
