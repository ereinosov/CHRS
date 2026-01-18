package org.uteq.backend.usuario.entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//s
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
}