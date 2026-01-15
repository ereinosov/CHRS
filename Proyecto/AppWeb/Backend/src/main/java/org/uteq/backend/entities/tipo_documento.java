package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_documento")

public class tipo_documento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_tipo_documento;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name="obligatorio", nullable = false)
    private Boolean obligatorio;
}
