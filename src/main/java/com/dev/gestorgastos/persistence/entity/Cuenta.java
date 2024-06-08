package com.dev.gestorgastos.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Integer idCuenta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @Column(name = "fondos")
    private Long fondos;

    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "id_denominacion")
    private Integer idDenominacion;

    @Column(name = "id_persona")
    private Integer idPersona;


    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_proveedor",insertable = false,updatable = false)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_denominacion",insertable = false,updatable = false)
    private Denominacion denominacion;

    @ManyToOne
    @JoinColumn(name = "id_persona",insertable = false,updatable = false)
    private Persona persona;


    @OneToMany(mappedBy = "cuenta")
    private List<PresupuestoMovimiento> presupuestosMovimientos;

    @OneToMany(mappedBy = "cuentaEgreso")
    private List<PresupuestoTransaccion> presupuestosTransaccionesEgresos;

    @OneToMany(mappedBy = "cuentaIngreso")
    private List<PresupuestoTransaccion> presupuestosTransaccionesIngresos;

    @OneToMany(mappedBy = "cuenta")
    private List<Movimiento> movimientos;

    @OneToMany(mappedBy = "cuentaEgreso")
    private List<Transaccion> transaccionesEgresos;

    @OneToMany(mappedBy = "cuentaIngreso")
    private List<Transaccion> transaccionesIngresos;

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

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdDenominacion() {
        return idDenominacion;
    }

    public void setIdDenominacion(Integer idDenominacion) {
        this.idDenominacion = idDenominacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Denominacion getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(Denominacion denominacion) {
        this.denominacion = denominacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<PresupuestoMovimiento> getPresupuestosMovimientos() {
        return presupuestosMovimientos;
    }

    public void setPresupuestosMovimientos(List<PresupuestoMovimiento> presupuestosMovimientos) {
        this.presupuestosMovimientos = presupuestosMovimientos;
    }

    public List<PresupuestoTransaccion> getPresupuestosTransaccionesEgresos() {
        return presupuestosTransaccionesEgresos;
    }

    public void setPresupuestosTransaccionesEgresos(List<PresupuestoTransaccion> presupuestosTransaccionesEgresos) {
        this.presupuestosTransaccionesEgresos = presupuestosTransaccionesEgresos;
    }

    public List<PresupuestoTransaccion> getPresupuestosTransaccionesIngresos() {
        return presupuestosTransaccionesIngresos;
    }

    public void setPresupuestosTransaccionesIngresos(List<PresupuestoTransaccion> presupuestosTransaccionesIngresos) {
        this.presupuestosTransaccionesIngresos = presupuestosTransaccionesIngresos;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public List<Transaccion> getTransaccionesEgresos() {
        return transaccionesEgresos;
    }

    public void setTransaccionesEgresos(List<Transaccion> transaccionesEgresos) {
        this.transaccionesEgresos = transaccionesEgresos;
    }

    public List<Transaccion> getTransaccionesIngresos() {
        return transaccionesIngresos;
    }

    public void setTransaccionesIngresos(List<Transaccion> transaccionesIngresos) {
        this.transaccionesIngresos = transaccionesIngresos;
    }
}