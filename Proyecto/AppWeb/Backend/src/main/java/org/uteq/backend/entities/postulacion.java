package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postulacion")
public class postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_postulacion;

    @ManyToOne
    @JoinColumn(name = "id_postulante", nullable = false, foreignKey = @ForeignKey(name = "fk_postulacion_postulante"))
    private postulante postulante;

    @ManyToOne
    @JoinColumn(name = "id_convocatoria", nullable = false, foreignKey = @ForeignKey(name = "fk_postulacion_convocatoria"))
    private convocatoria convocatoria;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "estado_postulacion", nullable = false)
    private String estado_postulacion = "pendiente";

    @Column(name = "preseleccion")
    private Boolean preseleccion;
}
