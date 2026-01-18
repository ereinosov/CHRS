package org.uteq.backend.usuario.repository;

import org.uteq.backend.usuario.entity.Usuario;

public interface IUsuarioRepository {
    Usuario validateAndSave(Usuario usuario);
    Usuario searchByUsername(String username);
}
