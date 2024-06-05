package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_movimiento")
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimiento")
    private Integer idTipoMovimiento;

    @Column(name = "nombre_tipo_movimiento")
    private String nombreTipoMovimiento;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "tipoMovimiento")
    private List<Movimiento> movimientos;

    @OneToMany(mappedBy = "tipoMovimiento")
    private List<PresupuestoMovimiento> presupuestosMovimientos;

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

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<PresupuestoMovimiento> getPresupuestosMovimientos() {
        return presupuestosMovimientos;
    }

    public void setPresupuestosMovimientos(List<PresupuestoMovimiento> presupuestosMovimientos) {
        this.presupuestosMovimientos = presupuestosMovimientos;
    }
}
