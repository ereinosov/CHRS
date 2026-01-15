package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class criterio_evaluacion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_area;
    @Column(name="nombre_area", nullable=false)
    private String nombre_area;



}
