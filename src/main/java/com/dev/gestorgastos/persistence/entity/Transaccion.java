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

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Long getMontoEgreso() {
        return montoEgreso;
    }

    public void setMontoEgreso(Long montoEgreso) {
        this.montoEgreso = montoEgreso;
    }

    public Long getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(Long montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public Integer getIdCuentaEgreso() {
        return idCuentaEgreso;
    }

    public void setIdCuentaEgreso(Integer idCuentaEgreso) {
        this.idCuentaEgreso = idCuentaEgreso;
    }

    public Integer getIdCuentaIngreso() {
        return idCuentaIngreso;
    }

    public void setIdCuentaIngreso(Integer idCuentaIngreso) {
        this.idCuentaIngreso = idCuentaIngreso;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public Integer getIdPresupuestoTransaccion() {
        return idPresupuestoTransaccion;
    }

    public void setIdPresupuestoTransaccion(Integer idPresupuestoTransaccion) {
        this.idPresupuestoTransaccion = idPresupuestoTransaccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Cuenta getCuentaEgreso() {
        return cuentaEgreso;
    }

    public void setCuentaEgreso(Cuenta cuentaEgreso) {
        this.cuentaEgreso = cuentaEgreso;
    }

    public Cuenta getCuentaIngreso() {
        return cuentaIngreso;
    }

    public void setCuentaIngreso(Cuenta cuentaIngreso) {
        this.cuentaIngreso = cuentaIngreso;
    }

    public PresupuestoTransaccion getPresupuestoTransaccion() {
        return presupuestoTransaccion;
    }

    public void setPresupuestoTransaccion(PresupuestoTransaccion presupuestoTransaccion) {
        this.presupuestoTransaccion = presupuestoTransaccion;
    }
}
