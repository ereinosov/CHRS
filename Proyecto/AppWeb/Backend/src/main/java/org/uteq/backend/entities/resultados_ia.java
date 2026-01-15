package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resultados_ia")
public class resultados_ia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_resultado_ia;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false, foreignKey = @ForeignKey(name = "fk_resultado_documento"))
    private documento documento;

    @Column(name = "resultado", nullable = false)
    private String resultado;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "fecha_revision", nullable = false)
    private LocalDateTime fecha_revision;
}
