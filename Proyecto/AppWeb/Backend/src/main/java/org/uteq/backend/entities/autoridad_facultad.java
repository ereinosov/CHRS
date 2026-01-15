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

public class autoridad_facultad {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_autoridad", nullable = false, foreignKey = @ForeignKey(name = "fk_af_autoridad"))
    private autoridad_academica autoridad_academica;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_facultad", nullable = false, foreignKey = @ForeignKey(name = "fk_af_facultad"))
    private facultad facultad;
}
