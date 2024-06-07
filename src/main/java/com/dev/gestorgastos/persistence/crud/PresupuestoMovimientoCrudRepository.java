package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PresupuestoMovimientoCrudRepository extends CrudRepository<PresupuestoMovimiento,Integer> {

    Optional<PresupuestoMovimiento> findByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento);
    Optional<List<PresupuestoMovimiento>> findAllByActivoTrueOrderByIdTipoMovimientoAsc();
    Optional<List<PresupuestoMovimiento>> findAllByActivoFalseOrderByIdTipoMovimientoAsc();
    Optional<List<PresupuestoMovimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByIdTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<PresupuestoMovimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByIdTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<PresupuestoMovimiento>> findByIdTipoMovimientoAndActivoTrue(Integer idTipoMovimiento);
    Optional<List<PresupuestoMovimiento>> findByIdCuentaAndActivoTrueOrderByIdTipoMovimientoAsc(Integer idCuenta);
    Optional<List<PresupuestoMovimiento>> findByIdPlanAndActivoTrueOrderByIdTipoMovimientoAsc(Integer idPlan);
    @Transactional
    @Modifying
    @Query("UPDATE PresupuestoMovimiento p SET p.activo = :activo WHERE p.idPresupuestoMovimiento= :idPresupuestoMovimiento")
    void setActivoByIdPresupuesto(Integer idPresupuestoMovimiento, boolean activo);
}