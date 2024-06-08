package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransaccionCrudRepository extends CrudRepository<Transaccion,Integer> {

    Optional<Transaccion> findByIdTransaccion(Integer idTransaccion);
    Optional<List<Transaccion>> findAllByActivoTrueOrderByIdTipoTransaccionAsc();
    Optional<List<Transaccion>> findAllByActivoFalseOrderByIdTipoTransaccionAsc();
    Optional<List<Transaccion>> findByTipoTransaccionNombreTipoTransaccionContainingAndActivoTrueOrderByIdTipoTransaccionAsc(String nombreTipoTransaccion);
    Optional<List<Transaccion>> findByTipoTransaccionNombreTipoTransaccionContainingAndActivoFalseOrderByIdTipoTransaccionAsc(String nombreTipoTransaccion);
    Optional<List<Transaccion>> findByIdTipoTransaccionAndActivoTrue(Integer idTipoTransaccion);
    Optional<List<Transaccion>> findByIdCuentaEgresoAndActivoTrueOrderByIdTipoTransaccionAsc(Integer idCuentaEgreso);
    Optional<List<Transaccion>> findByIdCuentaIngresoAndActivoTrueOrderByIdTipoTransaccionAsc(Integer idCuentaIngreso);
    Optional<List<Transaccion>> findByIdPresupuestoTransaccionAndActivoTrueOrderByIdTipoMovimientoAsc(Integer idPresupuestoTransaccion);
    Optional<List<Transaccion>> findByFechaHoraBetweenAndActivoTrueOrderByFechaHoraAsc(LocalDateTime startDateTime, LocalDateTime endDateTime);
    @Transactional
    @Modifying
    @Query("UPDATE Transaccion p SET p.activo = :activo WHERE p.idTransaccion = :idTransaccion")
    void setActivoByIdTransaccion(Integer idTransaccion, boolean activo);
}