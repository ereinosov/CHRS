package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entrevista")
public class entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_entrevista;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false, foreignKey = @ForeignKey(name = "fk_entrevista_postulacion"))
    private postulacion postulacion;

    @ManyToOne
    @JoinColumn(name = "id_decano", nullable = false, foreignKey = @ForeignKey(name = "fk_entrevista_decano"))
    private autoridad_academica decano;

    @ManyToOne
    @JoinColumn(name = "id_coordinador", nullable = false, foreignKey = @ForeignKey(name = "fk_entrevista_coordinador"))
    private autoridad_academica autoridad_academica;

    @Column(name = "fecha_entrevista")
    private LocalDateTime fecha_entrevista;

    @Column(name = "puntaje")
    private Double puntaje;

    @Column(name = "observaciones")
    private String observaciones;
}
