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
    Optional<List<Movimiento>> findAllByActivoTrueOrderByIdTipoMovimientoAsc();
    Optional<List<Movimiento>> findAllByActivoFalseOrderByIdTipoMovimientoAsc();
    Optional<List<Movimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByIdTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Movimiento>> findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByIdTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<Movimiento>> findByIdTipoMovimientoAndActivoTrue(Integer idTipoMovimiento);
    Optional<List<Movimiento>> findByIdCuentaAndActivoTrueOrderByIdTipoMovimientoAsc(Integer idCuenta);
    Optional<List<Movimiento>> findByIdPresupuestoAndActivoTrueOrderByIdTipoMovimientoAsc(Integer idPresupuesto);
    @Transactional
    @Modifying
    @Query("UPDATE Movimiento p SET p.activo = :activo WHERE p.idMovimiento = :idMovimiento")
    void setActivoByIdMovimiento(Integer idMovimiento, boolean activo);
}