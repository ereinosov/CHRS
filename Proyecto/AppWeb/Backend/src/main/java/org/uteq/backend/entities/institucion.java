package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "institucion")

public class institucion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_institucion;
    @Column(name = "nombre", nullable = false)
    private  String nombre;
    @Column(name="direccion",nullable=false)
    private String direccion;
    @Column(name="correo",nullable=false)
    private String correo;
    @Column(name="telefono")
    private String telefono;
}
