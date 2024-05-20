package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "denominaciones")
public class Denominacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_denominacion")
    private Integer idDenominacion;

    @Column(name = "nombre_denominacion")
    private String nombreDenominacion;

    @OneToMany(mappedBy = "denominacion")
    private List<Cuenta> cuentas;

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

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
}
