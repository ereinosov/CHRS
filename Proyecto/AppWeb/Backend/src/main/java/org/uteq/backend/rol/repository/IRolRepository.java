package org.uteq.backend.rol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uteq.backend.rol.entity.Rol;
import org.uteq.backend.rol.entity.RolNombre;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByUsername(String username);
    Optional<Rol> findByEmail(String email);

    // Verificar si existe un rol
    Boolean existsByNombre(RolNombre nombre);
}
