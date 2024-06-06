package com.dev.gestorgastos.domain.dto;

public class TipoTransaccionDto {
    private Integer idTipoTransaccion;
    private String nombreTipoTransaccion;
    private boolean activo;
    public TipoTransaccionDto() {
    }

    public TipoTransaccionDto(Integer idTipoTransaccion, String nombreTipoTransaccion, boolean activo) {
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombreTipoTransaccion = nombreTipoTransaccion;
        this.activo=activo;
    }

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getNombreTipoTransaccion() {
        return nombreTipoTransaccion;
    }

    public void setNombreTipoTransaccion(String nombreTipoTransaccion) {
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}