package org.uteq.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uteq.backend.entities.usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<usuario, Long> {
    Optional<usuario> findByUsuarioApp(String usuarioApp);
    Optional<usuario> findByUsuarioBd(String usuarioBd);
    boolean existsByUsuarioBd(String usuarioBd); // Asegúrate de que la 'u' sea minúscula
    boolean existsByUsuarioApp(String usuarioApp);
}