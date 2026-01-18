package org.uteq.backend.usuario.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.uteq.backend.usuario.entity.Usuario;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements  IUsuarioService {

    @Override
    public Usuario validateAndSave(Usuario usuario) {
        return null;
    }
}
