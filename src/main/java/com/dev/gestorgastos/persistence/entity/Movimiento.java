package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Column(name = "monto")
    private Long monto;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento",insertable = false,updatable = false)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_cuenta",insertable = false,updatable = false)
    private Cuenta cuenta;

    @OneToOne(mappedBy = "egreso")
    private Transaccion transaccionEgreso;

    @OneToOne(mappedBy = "ingreso")
    private Transaccion transaccionIngreso;
}
