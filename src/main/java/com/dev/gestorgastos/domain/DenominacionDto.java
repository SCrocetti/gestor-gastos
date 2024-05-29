package com.dev.gestorgastos.domain;

public class DenominacionDto {
    private Integer idDenominacion;
    private String nombreDenominacion;

    public DenominacionDto() {
    }

    public DenominacionDto(Integer idDenominacion, String nombreDenominacion) {
        this.idDenominacion = idDenominacion;
        this.nombreDenominacion = nombreDenominacion;
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
}
