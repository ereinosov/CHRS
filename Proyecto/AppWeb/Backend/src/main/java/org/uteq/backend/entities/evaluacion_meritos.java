package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evaluacion_meritos")
public class evaluacion_meritos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false, foreignKey = @ForeignKey(name = "fk_eval_postulacion"))
    private postulacion postulacion;

    @ManyToOne
    @JoinColumn(name = "id_decano", nullable = false, foreignKey = @ForeignKey(name = "fk_eval_decano"))
    private autoridad_academica decano;

    @ManyToOne
    @JoinColumn(name = "id_coordinador", nullable = false, foreignKey = @ForeignKey(name = "fk_eval_coordinador"))
    private autoridad_academica coordinador;

    @Column(name = "puntaje")
    private BigDecimal puntaje;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_evaluacion")
    private LocalDate fecha_evaluacion;
}
