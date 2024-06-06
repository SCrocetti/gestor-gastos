package com.dev.gestorgastos.domain.dto;

import java.time.LocalDateTime;

public class MovimientoDto {

    private Integer idMovimiento;

    private Long monto;

    private LocalDateTime fechaHora;

    private Integer idTipoMovimiento;

    private Integer idCuenta;

    private Integer idPresupuestoMovimiento;

    private boolean activo;

    public MovimientoDto() {
    }

    public MovimientoDto(Integer idMovimiento, Long monto, LocalDateTime fechaHora, Integer idTipoMovimiento, Integer idCuenta, Integer idPresupuestoMovimiento, boolean activo) {
        this.idMovimiento = idMovimiento;
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.idTipoMovimiento = idTipoMovimiento;
        this.idCuenta = idCuenta;
        this.idPresupuestoMovimiento = idPresupuestoMovimiento;
        this.activo = activo;
    }

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
}
