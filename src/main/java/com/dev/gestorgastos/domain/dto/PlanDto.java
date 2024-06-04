package com.dev.gestorgastos.domain.dto;

import java.time.LocalDate;

public class PlanDto {
    private Integer idPlan;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private boolean activo = true;

    public PlanDto() {
    }

    public PlanDto(Integer idPlan, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, boolean activo) {
        this.idPlan = idPlan;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
