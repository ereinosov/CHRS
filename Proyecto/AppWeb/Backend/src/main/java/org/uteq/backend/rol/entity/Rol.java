package org.uteq.backend.rol.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 20)
    private RolNombre nombre;

    // Constructores
    public Rol() {
    }

    public Rol(RolNombre nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolNombre getNombre() {
        return nombre;
    }

    public void setNombre(RolNombre nombre) {
        this.nombre = nombre;
    }

}
