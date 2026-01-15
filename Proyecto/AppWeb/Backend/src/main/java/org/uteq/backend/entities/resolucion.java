package org.uteq.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class resolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private  Long id_resolucion;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private  solicitud_docente solicitud_docente;

    @ManyToOne
    @JoinColumn(name = "id_aprovador", nullable = false)
    private autoridad_academica autoridad_academica;

    @Column(name =  "numero_resolucion")
    private String numero_resolucion;

    @Column(name =  "fecha_emision", nullable = false)
    private LocalDateTime fecha_emision;

    @Column(name = "observaciones", nullable = true)
    private String observaciones;

    @Column(name = "estado_solicitud", nullable = true)
    private String estado_solicitud;
}
