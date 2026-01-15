package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "convocatoria")
public class convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_convocatoria;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false, foreignKey = @ForeignKey(name = "fk_convocatoria_solicitud"))
    private solicitud_docente solicitud_docente;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "numero_vacantes", nullable = false)
    private Long numero_vacantes;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fecha_publicacion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fecha_fin;

    @Column(name = "estado_convocatoria", nullable = false)
    private String estado_convocatoria;
}
