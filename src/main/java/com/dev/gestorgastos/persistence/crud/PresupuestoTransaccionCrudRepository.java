package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import com.dev.gestorgastos.persistence.entity.PresupuestoTransaccion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PresupuestoTransaccionCrudRepository extends CrudRepository<PresupuestoTransaccion,Integer> {

    Optional<PresupuestoTransaccion> findByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion);
    Optional<List<PresupuestoTransaccion>> findAllByActivoTrueOrderByIdTipoTransaccionAsc();
    Optional<List<PresupuestoTransaccion>> findAllByActivoFalseOrderByIdTipoTransaccionAsc();
    Optional<List<PresupuestoTransaccion>> findByTipoTransaccionNombreTipoTransaccionContainingAndActivoTrueOrderByIdTipoTransaccionAsc(String nombreTipoTransaccion);
    Optional<List<PresupuestoTransaccion>> findByTipoTransaccionNombreTipoTransaccionContainingAndActivoFalseOrderByIdTipoTransaccionAsc(String nombreTipoTransaccion);
    Optional<List<PresupuestoTransaccion>> findByIdTipoTransaccionAndActivoTrue(Integer idTipoTransaccion);
    Optional<List<PresupuestoTransaccion>> findByIdCuentaEgresoAndActivoTrueOrderByIdTipoTransaccionAsc(Integer idCuentaEgreso);
    Optional<List<PresupuestoTransaccion>> findByIdCuentaIngresoAndActivoTrueOrderByIdTipoTransaccionAsc(Integer idCuentaIngreso);
    Optional<List<PresupuestoTransaccion>> findByIdPlanAndActivoTrueOrderByIdTipoTransaccionAsc(Integer idPlan);
    @Transactional
    @Modifying
    @Query("UPDATE PresupuestoTransaccion p SET p.activo = :activo WHERE p.idPresupuestoTransaccion= :idPresupuestoTransaccion")
    void setActivoByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion, boolean activo);
}