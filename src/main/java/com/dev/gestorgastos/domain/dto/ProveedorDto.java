package com.dev.gestorgastos.domain.dto;

public class ProveedorDto {
    private Integer idProveedor;
    private String nombreProveedor;
    private boolean activo=true;
    public ProveedorDto() {
    }

    public ProveedorDto(Integer idProveedor, String nombreProveedor, boolean activo) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.activo=activo;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}