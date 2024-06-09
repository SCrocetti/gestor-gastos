package com.dev.gestorgastos.domain.dto;

public class CuentaDto {
    private Integer idCuenta;
    private String descripcion;
    private String numeroCuenta;
    private Long fondos;
    private Integer idProveedor;
    private Integer idDenominacion;
    private Integer idPersona;
    private boolean activo=true;
    public CuentaDto() {
    }

    public CuentaDto(Integer idCuenta, String descripcion, String numeroCuenta, Long fondos, Integer idDenominacion, Integer idProveedor, Integer idPersona, boolean activo) {
        this.idCuenta = idCuenta;
        this.descripcion = descripcion;
        this.numeroCuenta = numeroCuenta;
        this.fondos = fondos;
        this.idDenominacion = idDenominacion;
        this.idProveedor = idProveedor;
        this.idPersona = idPersona;
        this.activo=activo;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Long getFondos() {
        return fondos;
    }

    public void setFondos(Long fondos) {
        this.fondos = fondos;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    public void setIdDenominacion(Integer idDenominacion) {
        this.idDenominacion = idDenominacion;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
