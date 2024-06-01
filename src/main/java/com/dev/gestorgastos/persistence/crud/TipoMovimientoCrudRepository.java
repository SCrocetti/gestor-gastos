package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TipoMovimientoCrudRepository extends CrudRepository<TipoMovimiento,Integer> {

    Optional<TipoMovimiento> findByIdTipoMovimiento(Integer idTipoMovimiento);
    Optional<List<TipoMovimiento>> findByNombreTipoMovimientoContainingAndActivoTrueOrderByNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<TipoMovimiento>> findByNombreTipoMovimientoContainingAndActivoFalseOrderByNombreTipoMovimientoAsc(String nombreTipoMovimiento);
    Optional<List<TipoMovimiento>> findAllByActivoTrueOrderByNombreTipoMovimientoAsc();
    Optional<List<TipoMovimiento>> findAllByActivoFalseOrderByNombreTipoMovimientoAsc();
    @Transactional
    @Modifying
    @Query("UPDATE TipoMovimiento t SET t.activo = :activo WHERE t.idTipoMovimiento = :idTipoMovimiento")
    void setActivoByIdTipoMovimiento(Integer idTipoMovimiento, boolean activo);
}