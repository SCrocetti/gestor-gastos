package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tipos_transaccion")
public class TipoTransaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_transaccion")
    private Integer idTipoTransaccion;

    @Column(name = "nombre_tipo_transaccion")
    private String nombreTipoTransaccion;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "tipoTransaccion")
    private List<Transaccion> transacciones;

    @OneToMany(mappedBy = "tipoTransaccion")
    private List<PresupuestoTransaccion> presupuestosTransacciones;

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

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public List<PresupuestoTransaccion> getPresupuestosTransacciones() {
        return presupuestosTransacciones;
    }

    public void setPresupuestosTransacciones(List<PresupuestoTransaccion> presupuestosTransacciones) {
        this.presupuestosTransacciones = presupuestosTransacciones;
    }
}
