package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documento")
public class documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_documento;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false, foreignKey = @ForeignKey(name = "fk_documento_postulacion"))
    private postulacion postulacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false, foreignKey = @ForeignKey(name = "fk_documento_tipo"))
    private tipo_documento tipo_documento;

    @Column(name = "fecha_carga", nullable = false)
    private LocalDateTime fecha_carga;

    @Column(name = "estado_validacion", nullable = false)
    private String estado_validacion;

    @Column(name = "ruta_archivo", nullable = false, columnDefinition = "text")
    private String ruta_archivo;
}
