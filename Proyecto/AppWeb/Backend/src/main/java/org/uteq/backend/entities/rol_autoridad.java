package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol_autoridad")
public class rol_autoridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_rol;

    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "descripcion",  nullable = false)
    private String descripcion;
}
