package com.dev.gestorgastos.domain.dto;

public class PersonaDto {
    private Integer idPersona;

    private String numeroDocumento;

    private String nombres;

    private String apellidos;

    private boolean activo=true;

    public PersonaDto() {
    }

    public PersonaDto(Integer idPersona, String numeroDocumento, String nombres, String apellidos, boolean activo) {
        this.idPersona = idPersona;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.activo=activo;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
