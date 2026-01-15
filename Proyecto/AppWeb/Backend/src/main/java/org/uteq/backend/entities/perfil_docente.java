package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "perfil_docente")
public class perfil_docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_perfil;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false, foreignKey = @ForeignKey(name = "fk_perfil_solicitud"))
    private solicitud_docente solicitud;

    @Column(name = "nivel_academico", nullable = false)
    private String nivel_academico;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_area", nullable = false, foreignKey = @ForeignKey(name = "fk_perfil_area"))
    private area_conocimiento area;

    @Column(name = "experiencia_profesional_min", nullable = false)
    private Long experiencia_profesional_min;

    @Column(name = "experiencia_docente_min", nullable = false)
    private Long experiencia_docente_min;
}
