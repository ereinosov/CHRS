package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class convocatoria_materia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id_convocatoria_materia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_convocatoria", foreignKey = @ForeignKey(name="fk_cm_convocatoria"), nullable = false)
    private convocatoria convocatoria;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_materia", foreignKey = @ForeignKey(name="fk_cm_materia"), nullable = false)
    private materia materia;

}
