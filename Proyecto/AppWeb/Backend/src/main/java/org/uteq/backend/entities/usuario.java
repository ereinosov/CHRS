package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(name = "usuario_bd")
    private String usuarioBd;

    @Column(name = "clave_bd")
    private String claveBd;

    @Column(name = "usuario_app")
    private String usuarioApp;

    @Column(name = "clave_app")
    private String claveApp;

    @Column(name = "rol")
    private String rol;

    @Column(name = "id_rol")
    private Long id_rol;

    @Column(name = "activo")
    private Boolean activo;
}