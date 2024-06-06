package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "planes")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Integer idPlan;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "plan")
    private List<PresupuestoMovimiento> presupuestosMovimientos;

    @OneToMany(mappedBy = "plan")
    private List<PresupuestoTransaccion> presupuestosTransacciones;

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

    public List<PresupuestoMovimiento> getPresupuestosMovimientos() {
        return presupuestosMovimientos;
    }

    public void setPresupuestosMovimientos(List<PresupuestoMovimiento> presupuestosMovimientos) {
        this.presupuestosMovimientos = presupuestosMovimientos;
    }

    public List<PresupuestoTransaccion> getPresupuestosTransacciones() {
        return presupuestosTransacciones;
    }

    public void setPresupuestosTransacciones(List<PresupuestoTransaccion> presupuestosTransacciones) {
        this.presupuestosTransacciones = presupuestosTransacciones;
    }
}
