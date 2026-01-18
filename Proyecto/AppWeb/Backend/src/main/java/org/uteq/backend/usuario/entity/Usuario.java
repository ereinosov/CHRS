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

    private Set<Rol> roles = new HashSet<>();

    //Getters and setters
    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario_bd() {
        return usuario_bd;
    }

    public void setUsuario_bd(String usuario_bd) {
        this.usuario_bd = usuario_bd;
    }

    public String getClave_bd() {
        return clave_bd;
    }

    public void setClave_bd(String clave_bd) {
        this.clave_bd = clave_bd;
    }

    public String getUsuario_app() {
        return usuario_app;
    }

    public void setUsuario_app(String usuario_app) {
        this.usuario_app = usuario_app;
    }

    public String getClave_app() {
        return clave_app;
    }

    public void setClave_app(String clave_app) {
        this.clave_app = clave_app;
    }


    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}