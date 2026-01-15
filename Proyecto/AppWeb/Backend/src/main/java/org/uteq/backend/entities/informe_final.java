package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "informe_final")
public class informe_final {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_informe;

    @ManyToOne
    @JoinColumn(name = "id_postulacion", nullable = false, foreignKey = @ForeignKey(name = "fk_informe_postulacion"))
    private postulacion postulacion;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fecha_emision;

    @Column(name = "resultado_final", nullable = false)
    private String resultado_final;

    @Column(name = "observaciones", nullable = false)
    private String observaciones;
}
