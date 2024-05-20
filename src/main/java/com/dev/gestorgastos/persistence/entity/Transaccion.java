package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idPais;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_egreso")
    private Integer idEgreso;

    @Column(name = "id_ingreso")
    private Integer idIngreso;

    @OneToOne
    @JoinColumn(name = "id_egreso", referencedColumnName = "id_movimiento")
    private Movimiento egreso;

    @OneToOne
    @JoinColumn(name = "id_ingreso", referencedColumnName = "id_movimiento")
    private Movimiento ingreso;
}
