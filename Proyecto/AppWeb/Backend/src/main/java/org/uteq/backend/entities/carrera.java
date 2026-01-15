package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class carrera {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id_carrera;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_facultad", foreignKey = @ForeignKey(name = "fk_carrera_facultad"), nullable = false)
    private facultad facultad;

    @Column(name ="nombres_carrera", nullable = false)
    private String nombres_carrera;
    @Column(name ="modalidad", nullable = false)
    private String modalidad;
    @Column(name ="estado", nullable = false)
    private Boolean estado;
}
