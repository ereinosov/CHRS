package org.uteq.backend.usuario.service;
import org.uteq.backend.usuario.entity.Usuario;

public interface IUsuarioService {
    Usuario validateAndSave(Usuario usuario);
}
