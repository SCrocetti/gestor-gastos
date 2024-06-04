package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Movimiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MovimientoCrudRepository extends CrudRepository<Movimiento,Integer> {

    Optional<Movimiento> findByIdMovimiento(Integer idMovimiento);
    Optional<List<Movimiento>> findAllByActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc();
    Optional<List<Movimiento>> findAllByActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc();
    Optional<List<Movimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Movimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Movimiento>> findByIdTipoMovimientoAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(Integer idTipoMovimiento);
    Optional<List<Movimiento>> findByIdCuentaAndActivoTrueOrderByCuentaDescripcionAsc(Integer idCuenta);
    Optional<List<Movimiento>> findByIdPresupuestoAndActivoTrueOrderByIdPresupuestoAsc(Integer idPresupuesto);
    @Transactional
    @Modifying
    @Query("UPDATE Movimiento p SET p.activo = :activo WHERE p.idMovimiento = :idMovimiento")
    void setActivoByIdMovimiento(Integer idMovimiento, boolean activo);
}