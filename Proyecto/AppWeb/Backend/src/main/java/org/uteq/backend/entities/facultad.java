package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class facultad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_facultad;
    @Column(name ="nombre_facultad", unique = true, nullable = false)
    private String nombre_facultad;
    @Column(name ="estado", nullable = false)
    private Boolean estado;
}
