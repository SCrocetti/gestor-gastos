package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;


    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "fondos")
    private Long fondos;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_denominacion")
    private Integer idDenominacioon;

    @Column(name = "id_persona")
    private Integer idPersona;

    @ManyToOne
    @JoinColumn(name = "id_proveedor",insertable = false,updatable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_denominacion",insertable = false,updatable = false)
    private Denominacion denominacion;

    @ManyToOne
    @JoinColumn(name = "id_persona",insertable = false,updatable = false)
    private Persona persona;

    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;
}