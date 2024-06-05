package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @Column(name = "monto_egreso")
    private Long montoEgreso;

    @Column(name = "monto_ingreso")
    private Long montoIngreso;

    @Column(name = "id_cuenta_egreso", insertable = false, updatable = false)
    private Integer idCuentaEgreso;

    @Column(name = "id_cuenta_ingreso", insertable = false, updatable = false)
    private Integer idCuentaIngreso;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "id_tipo_transaccion")
    private Integer idTipoTransaccion;

    @Column(name = "id_presupuesto_transaccion")
    private Integer idPresupuestoTransaccion;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;


    @ManyToOne
    @JoinColumn(name = "id_tipo_transaccion",insertable = false,updatable = false)
    private TipoTransaccion tipoTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_egreso",insertable = false,updatable = false)
    private Cuenta cuentaEgreso;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_ingreso",insertable = false,updatable = false)
    private Cuenta cuentaIngreso;

    @ManyToOne
    @JoinColumn(name = "id_presupuesto_transaccion",insertable = false,updatable = false)
    private PresupuestoTransaccion presupuestoTransaccion;


}
