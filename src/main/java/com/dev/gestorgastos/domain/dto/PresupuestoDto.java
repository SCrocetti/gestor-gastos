package com.dev.gestorgastos.domain.dto;

public class PresupuestoDto {
    private Integer idPresupuesto;

    private Long montoAsignado;

    private Long montoEjecutado;

    private Integer idTipoMovimiento;

    private Integer idCuenta;

    private Integer idPlan;

    private boolean activo = true;

    public PresupuestoDto() {
    }

    public PresupuestoDto(Integer idPresupuesto, Long montoAsignado, Long montoEjecutado, Integer idTipoMovimiento, Integer idPlan, Integer idCuenta, boolean activo) {
        this.idPresupuesto = idPresupuesto;
        this.montoAsignado = montoAsignado;
        this.montoEjecutado = montoEjecutado;
        this.idTipoMovimiento = idTipoMovimiento;
        this.idPlan = idPlan;
        this.idCuenta = idCuenta;
        this.activo = activo;
    }

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Long getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(Long montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public Long getMontoEjecutado() {
        return montoEjecutado;
    }

    public void setMontoEjecutado(Long montoEjecutado) {
        this.montoEjecutado = montoEjecutado;
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
