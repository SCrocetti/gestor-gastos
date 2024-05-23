package com.dev.gestorgastos.domain;

public class PaisDto {
    private Integer idPais;

    private String nombrePais;

    public PaisDto() {
    }

    public PaisDto(Integer idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}
