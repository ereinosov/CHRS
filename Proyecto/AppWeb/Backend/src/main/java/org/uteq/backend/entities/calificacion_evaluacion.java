package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calificacion_evaluacion")
public class calificacion_evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id_calificacion;

    @ManyToOne
    @JoinColumn(name = "id_evaluacion", nullable = false, foreignKey = @ForeignKey(name = "fk_calificacion_evaluacion"))
    private evaluacion_meritos evaluacion_meritos;

    @ManyToOne
    @JoinColumn(name = "id_criterio", nullable = false, foreignKey = @ForeignKey(name = "fk_calificacion_criterio"))
    private criterio_evaluacion criterio_evaluacion;

    @Column(name = "puntaje_obtenido", nullable = false)
    private BigDecimal puntaje_obtenido;

    @Column(name = "observaciones")
    private String observaciones;
    //vv
}
