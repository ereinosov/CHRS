package org.uteq.backend.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.backend.usuario.entity.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario validateAndSave(Usuario usuario);
    Usuario searchByUsername(String username);

    @Override
    Optional<Usuario> findById(Long aLong);

    // Buscar usuario por username
    Optional<Usuario> findByUsername(String username);

    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);

    // Verificar si existe un username
    Boolean existsByUsername(String username);

    // Verificar si existe un email
    Boolean existsByEmail(String email);
}
