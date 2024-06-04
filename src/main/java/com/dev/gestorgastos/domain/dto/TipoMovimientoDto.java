package com.dev.gestorgastos.domain.dto;

public class TipoMovimientoDto {
    private Integer idTipoMovimiento;
    private String nombreTipoMovimiento;
    private boolean activo;
    public TipoMovimientoDto() {
    }

    public TipoMovimientoDto(Integer idTipoMovimiento, String nombreTipoMovimiento, boolean activo) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombreTipoMovimiento = nombreTipoMovimiento;
        this.activo=activo;
    }

    public Integer getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(Integer idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombreTipoMovimiento() {
        return nombreTipoMovimiento;
    }

    public void setNombreTipoMovimiento(String nombreTipoMovimiento) {
        this.nombreTipoMovimiento = nombreTipoMovimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}