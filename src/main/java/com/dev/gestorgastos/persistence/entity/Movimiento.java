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

    @Column(name = "id_presupuesto_movimiento")
    private Integer idPresupuestoMovimiento;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento",insertable = false,updatable = false)
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_cuenta",insertable = false,updatable = false)
    private Cuenta cuenta;

    @ManyToOne
    @JoinColumn(name = "id_presupuesto_movimiento",insertable = false,updatable = false)
    private PresupuestoMovimiento presupuestoMovimiento;

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getIdPresupuestoMovimiento() {
        return idPresupuestoMovimiento;
    }

    public void setIdPresupuestoMovimiento(Integer idPresupuestoMovimiento) {
        this.idPresupuestoMovimiento = idPresupuestoMovimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public PresupuestoMovimiento getPresupuestoMovimiento() {
        return presupuestoMovimiento;
    }

    public void setPresupuestoMovimiento(PresupuestoMovimiento presupuestoMovimiento) {
        this.presupuestoMovimiento = presupuestoMovimiento;
    }

}
