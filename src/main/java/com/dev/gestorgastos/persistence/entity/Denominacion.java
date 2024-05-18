package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "denominaciones")
public class Denominacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_denominacion")
    private Integer idDenominacion;

    @Column(name = "nombre_denominacion")
    private String nombreDenominacion;
}
