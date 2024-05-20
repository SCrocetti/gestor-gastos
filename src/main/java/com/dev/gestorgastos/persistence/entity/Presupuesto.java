package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "presupuestos")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto")
    private Integer idPresupuesto;


    @Column(name = "monto_asignado")
    private Long montoAsignado;

    @Column(name = "monto_ejecutado")
    private Long montoEjecutado;

    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "id_plan")
    private Integer idPlan;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento",insertable = false,updatable = false)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_cuenta",insertable = false,updatable = false)
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "id_plan",insertable = false,updatable = false)
    private Plan plan;

    @OneToMany(mappedBy = "presupuesto")
    private List<Movimiento> movimientos;
}
