package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class postulante {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id_postulante;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name="fk_postulante_usuario"), nullable = false)
    private usuario usuario;
    @Column(name ="nombres_postulante", nullable = false)
    private String nombres_postulante;
    @Column(name ="apellidos_postulante", nullable = false)
    private String apellidos_postulante;
    @Column(name ="identificacion", unique = true, nullable = false)
    private String identificacion;
    @Column(name ="correo_postulante", nullable = false)
    private String correo_postulante;
    @Column(name ="telefono_postulante", nullable = false)
    private String telefono_postulante;
    @Column(name ="fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;
}
