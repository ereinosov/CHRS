package org.uteq.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_materia;

    @ManyToOne
    @JoinColumn(name = "id_carrera", foreignKey = @ForeignKey(name = "fk_materia_carrera"), nullable = false)
    private  carrera id_carrera;

    @Column(name = "nombre_materia",  nullable = false)
    private  String nombre_materia;

    @Column(name = "nivel", nullable = false)
    private  Long nivel;
}
