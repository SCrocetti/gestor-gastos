package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idPais;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_egreso", insertable = false, updatable = false)
    private Integer idEgreso;

    @Column(name = "id_ingreso", insertable = false, updatable = false)
    private Integer idIngreso;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToOne
    @JoinColumn(name = "id_egreso", referencedColumnName = "id_movimiento")
    private Movimiento egreso;

    @OneToOne
    @JoinColumn(name = "id_ingreso", referencedColumnName = "id_movimiento")
    private Movimiento ingreso;

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Movimiento getEgreso() {
        return egreso;
    }

    public void setEgreso(Movimiento egreso) {
        this.egreso = egreso;
    }

    public Movimiento getIngreso() {
        return ingreso;
    }

    public void setIngreso(Movimiento ingreso) {
        this.ingreso = ingreso;
    }
}
