package com.dev.gestorgastos.domain.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class TransaccionDto {

    private Integer idTransaccion;

    private Long montoEgreso;

    private Long montoIngreso;

    private Integer idCuentaEgreso;

    private Integer idCuentaIngreso;

    private LocalDateTime fechaHora;

    private Integer idTipoTransaccion;

    private Integer idPresupuestoTransaccion;

    private boolean activo=true;

    public TransaccionDto() {
    }

    public TransaccionDto(Integer idTransaccion, Long montoEgreso, Long montoIngreso, Integer idCuentaEgreso, Integer idCuentaIngreso, LocalDateTime fechaHora, Integer idTipoTransaccion, Integer idPresupuestoTransaccion, boolean activo) {
        this.idTransaccion = idTransaccion;
        this.montoEgreso = montoEgreso;
        this.montoIngreso = montoIngreso;
        this.idCuentaEgreso = idCuentaEgreso;
        this.idCuentaIngreso = idCuentaIngreso;
        this.fechaHora = fechaHora;
        this.idTipoTransaccion = idTipoTransaccion;
        this.idPresupuestoTransaccion = idPresupuestoTransaccion;
        this.activo = activo;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Long getMontoEgreso() {
        return montoEgreso;
    }

    public void setMontoEgreso(Long montoEgreso) {
        this.montoEgreso = montoEgreso;
    }

    public Long getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(Long montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public Integer getIdCuentaEgreso() {
        return idCuentaEgreso;
    }

    public void setIdCuentaEgreso(Integer idCuentaEgreso) {
        this.idCuentaEgreso = idCuentaEgreso;
    }

    public Integer getIdCuentaIngreso() {
        return idCuentaIngreso;
    }

    public void setIdCuentaIngreso(Integer idCuentaIngreso) {
        this.idCuentaIngreso = idCuentaIngreso;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(Integer idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public Integer getIdPresupuestoTransaccion() {
        return idPresupuestoTransaccion;
    }

    public void setIdPresupuestoTransaccion(Integer idPresupuestoTransaccion) {
        this.idPresupuestoTransaccion = idPresupuestoTransaccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
