package org.uteq.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class autoridad_carrera {
    @Id
    @OneToOne
    @JoinColumn(name = "id_autoridad", nullable = false, foreignKey = @ForeignKey(name = "fk_ac_autoload"))
    private  rol_autoridad rol_autoridad;

    @Id
    @OneToOne
    @JoinColumn(name = "id_carrera", nullable = false, foreignKey = @ForeignKey(name = "fk_ac_carrera"))
    private carrera carrera;

}
