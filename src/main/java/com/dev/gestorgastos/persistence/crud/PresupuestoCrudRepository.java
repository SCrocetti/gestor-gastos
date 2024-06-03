package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Presupuesto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PresupuestoCrudRepository extends CrudRepository<Presupuesto,Integer> {

    Optional<Presupuesto> findByIdPresupuesto(Integer idPresupuesto);
    Optional<List<Presupuesto>> findAllByActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc();
    Optional<List<Presupuesto>> findAllByActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc();
    Optional<List<Presupuesto>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Presupuesto>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Presupuesto>> findByIdTipoMovimientoAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(Integer idTipoMovimiento);
    Optional<List<Presupuesto>> findByIdCuentaAndActivoTrueOrderByCuentaDescripcionAsc(Integer idCuenta);
    Optional<List<Presupuesto>> findByIdPlanAndActivoTrueOrderByPlanDescripcionAsc(Integer idPlan);
    @Transactional
    @Modifying
    @Query("UPDATE Presupuesto p SET p.activo = :activo WHERE p.idPresupuesto = :idPresupuesto")
    void setActivoByIdPresupuesto(Integer idPresupuesto, boolean activo);
}