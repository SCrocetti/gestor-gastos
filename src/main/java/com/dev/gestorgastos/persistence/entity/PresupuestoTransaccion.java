package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "presupuestos_transacciones")
public class PresupuestoTransaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_presupuesto_transaccion")
    private Integer idPresupuestoTransaccion;


    @Column(name = "monto_debitar")
    private Long montoDebitar;

    @Column(name = "monto_debitado")
    private Long montoDebitado;

    @Column(name = "monto_acreditar")
    private Long montoAcreditar;

    @Column(name = "monto_acreditado")
    private Long montoAcreditado;

    @Column(name = "id_cuenta_egreso")
    private Integer idCuentaEgreso;

    @Column(name = "id_cuenta_ingreso")
    private Integer idCuentaIngreso;

    @Column(name = "id_tipo_transaccion")
    private Integer idTipoTransaccion;


    @Column(name = "id_plan")
    private Integer idPlan;

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
    @JoinColumn(name = "id_plan",insertable = false,updatable = false)
    private Plan plan;

    @OneToMany(mappedBy = "transaccion")
    private List<Transaccion> transacciones;

    public Integer getIdPresupuestoTransaccion() {
        return idPresupuestoTransaccion;
    }

    public void setIdPresupuestoTransaccion(Integer idPresupuestoTransaccion) {
        this.idPresupuestoTransaccion = idPresupuestoTransaccion;
    }

    public Long getMontoDebitar() {
        return montoDebitar;
    }

    public void setMontoDebitar(Long montoDebitar) {
        this.montoDebitar = montoDebitar;
    }

    public Long getMontoDebitado() {
        return montoDebitado;
    }

    public void setMontoDebitado(Long montoDebitado) {
        this.montoDebitado = montoDebitado;
    }

    public Long getMontoAcreditar() {
        return montoAcreditar;
    }

    public void setMontoAcreditar(Long montoAcreditar) {
        this.montoAcreditar = montoAcreditar;
    }

    public Long getMontoAcreditado() {
        return montoAcreditado;
    }

    public void setMontoAcreditado(Long montoAcreditado) {
        this.montoAcreditado = montoAcreditado;
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

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }
}
