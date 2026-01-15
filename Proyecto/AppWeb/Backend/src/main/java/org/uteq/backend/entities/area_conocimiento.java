package org.uteq.backend.entities;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "area_conocimiento")

public class area_conocimiento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_area;
    @Column(name = "nombre_area", nullable = false)
    private String nombre_area;
}
