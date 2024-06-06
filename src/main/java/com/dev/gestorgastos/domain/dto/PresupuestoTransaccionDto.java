package com.dev.gestorgastos.domain.dto;

import jakarta.persistence.Column;

public class PresupuestoTransaccionDto {
    private Integer idPresupuestoTransaccion;

    private Long montoDebitar;

    private Long montoDebitado;

    private Long montoAcreditar;

    private Long montoAcreditado;

    private Integer idCuentaEgreso;

    private Integer idCuentaIngreso;

    private Integer idTipoTransaccion;

    private Integer idPlan;

    private boolean activo = true;

    public PresupuestoTransaccionDto() {
    }

    public PresupuestoTransaccionDto(Integer idPresupuestoTransaccion, Long montoDebitar, Long montoDebitado, Long montoAcreditar, Long montoAcreditado, Integer idCuentaEgreso, Integer idCuentaIngreso, Integer idTipoTransaccion, Integer idPlan, boolean activo) {
        this.idPresupuestoTransaccion = idPresupuestoTransaccion;
        this.montoDebitar = montoDebitar;
        this.montoDebitado = montoDebitado;
        this.montoAcreditar = montoAcreditar;
        this.montoAcreditado = montoAcreditado;
        this.idCuentaEgreso = idCuentaEgreso;
        this.idCuentaIngreso = idCuentaIngreso;
        this.idTipoTransaccion = idTipoTransaccion;
        this.idPlan = idPlan;
        this.activo = activo;
    }

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
}
