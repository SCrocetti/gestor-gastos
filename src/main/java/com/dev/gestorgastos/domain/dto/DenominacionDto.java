package com.dev.gestorgastos.domain.dto;

public class DenominacionDto {
    private Integer idDenominacion;
    private String nombreDenominacion;
    private boolean activo;
    public DenominacionDto() {
    }

    public DenominacionDto(Integer idDenominacion, String nombreDenominacion, boolean activo) {
        this.idDenominacion = idDenominacion;
        this.nombreDenominacion = nombreDenominacion;
        this.activo=activo;
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    public void setIdDenominacion(Integer idDenominacion) {
        this.idDenominacion = idDenominacion;
    }

    public String getNombreDenominacion() {
        return nombreDenominacion;
    }

    public void setNombreDenominacion(String nombreDenominacion) {
        this.nombreDenominacion = nombreDenominacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
