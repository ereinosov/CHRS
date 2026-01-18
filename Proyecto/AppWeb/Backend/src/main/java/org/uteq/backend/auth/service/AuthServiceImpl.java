package org.uteq.backend.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.uteq.backend.auth.dto.LoginRequest;
import org.uteq.backend.auth.dto.LoginResponse;
import org.uteq.backend.auth.util.JwtUtil;
import org.uteq.backend.usuario.entity.Usuario;
import org.uteq.backend.usuario.repository.UsuarioRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UsuarioRepositoryImpl usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // Autenticar
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        // Obtener datos completos del usuario desde la BD
        Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername()) // ← AQUÍ
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<String> roles = usuario.getRoles().stream()
                .map(rol -> rol.getNombre().name())
                .collect(Collectors.toList());

        return new LoginResponse(token, "Bearer", usuario.getUsuario_app(), usuario.getClave_app(), roles);
    }
}
