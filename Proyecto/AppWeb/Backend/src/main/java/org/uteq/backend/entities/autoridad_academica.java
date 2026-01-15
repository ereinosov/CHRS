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

public class autoridad_academica {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id_autoridad;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String correo;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;

    @Column(nullable = false)
    private Boolean estado;

    @OneToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name="fk_autoridad_usuario"), nullable = false)
    private usuario id_usuario;

    @ManyToOne
    @JoinColumn(name = "id_institucion", foreignKey = @ForeignKey(name="fk_autoridad_institucion"),  nullable = false)
    private institucion id_institucion;

    @ManyToOne
    @JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name="fk_autoridad_rol"), nullable = false)
    private rol_autoridad rol_autoridad;

}
