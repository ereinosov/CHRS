package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitud_docente")
public class solicitud_docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    @ManyToOne
    @JoinColumn(name = "id_autoridad", nullable = false)
    private autoridad_academica autoridad;

    @Column(name = "fecha_solicitud", nullable = false)
    private Date fecha_solicitud;

    @Column(name = "estado_solicitud", nullable = false)
    private String estado_solicitud;

    @Column(name = "justificacion", nullable = false)
    private String justificacion;

    @Column(name = "cantidad_docentes", nullable = false)
    private Long cantidad_docentes;

}
