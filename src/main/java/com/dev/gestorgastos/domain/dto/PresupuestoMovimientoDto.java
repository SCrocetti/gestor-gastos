package com.dev.gestorgastos.domain.dto;

public class PresupuestoMovimientoDto {
    private Integer idPresupuestoMovimiento;

    private Long montoAsignado;

    private Long montoEjecutado;

    private Integer idTipoMovimiento;

    private Integer idCuenta;

    private Integer idPlan;

    private boolean activo = true;

    public PresupuestoMovimientoDto() {
    }

    public PresupuestoMovimientoDto(Integer idPresupuestoMovimiento, Long montoAsignado, Long montoEjecutado, Integer idTipoMovimiento, Integer idPlan, Integer idCuenta, boolean activo) {
        this.idPresupuestoMovimiento = idPresupuestoMovimiento;
        this.montoAsignado = montoAsignado;
        this.montoEjecutado = montoEjecutado;
        this.idTipoMovimiento = idTipoMovimiento;
        this.idPlan = idPlan;
        this.idCuenta = idCuenta;
        this.activo = activo;
    }

    public Integer getIdPresupuestoMovimiento() {
        return idPresupuestoMovimiento;
    }

    public void setIdPresupuestoMovimiento(Integer idPresupuestoMovimiento) {
        this.idPresupuestoMovimiento = idPresupuestoMovimiento;
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
