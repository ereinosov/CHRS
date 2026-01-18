package org.uteq.backend.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uteq.backend.usuario.entity.Usuario;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    @GetMapping
    public Usuario searchByEmail(String email) {
        return null;
    }

}
