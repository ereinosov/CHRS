package org.uteq.backend.service;

import org.uteq.backend.entities.usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    // Listar todos
    List<usuario> listarTodos();

    // Buscar por ID
    Optional<usuario> buscarPorId(Long id);

    // Crear nuevo usuario
    usuario crear(usuario usuario);

    // Actualizar usuario existente
    usuario actualizar(Long id, usuario usuario);

    // Eliminar usuario
    void eliminar(Long id);

    Optional<usuario> buscarPorUsuarioBd(String UsuarioBd);

    // Buscar por usuario BD
    Optional<usuario> buscarPorUsuarioApp(String usuarioApp);

    // Validar si existe
    boolean existeUsuarioBd(String usuarioBd);

    boolean existeUsuarioApp(String usuarioApp);
}