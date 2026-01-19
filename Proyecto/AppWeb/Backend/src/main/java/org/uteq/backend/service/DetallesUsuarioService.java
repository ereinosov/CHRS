package org.uteq.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.uteq.backend.entities.usuario;
import org.uteq.backend.repository.UsuarioRepository;

import java.util.Collections;

@Service
public class DetallesUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos al usuario usando TU columna 'usuario_app'
        usuario usuario = usuarioRepository.findByUsuarioApp(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. TRADUCCIÓN: Le pasamos tus datos a Spring Security
        // OJO: Aquí asignamos 'usuarioApp' como username y 'claveApp' como password
        return new User(
                usuario.getUsuarioApp(),
                usuario.getClaveApp(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")) // Rol por defecto para que no falle
        );
    }
}