package org.uteq.backend.usuario.entity;
import jakarta.persistence.*;
import lombok.*;
import org.uteq.backend.rol.entity.Rol;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//s
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_usuario;
    @Column(name ="usuario_bd", unique = true, nullable = false)
    private String usuario_bd;
    @Column(name ="clave_bd", nullable = false)
    private String clave_bd;
    @Column(name ="usuario_app", unique = true, nullable = false)
    private String usuario_app;
    @Column(name ="clave_app", nullable = false)
    private String clave_app;
    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}