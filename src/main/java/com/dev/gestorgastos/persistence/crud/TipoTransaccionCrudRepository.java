package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.TipoMovimiento;
import com.dev.gestorgastos.persistence.entity.TipoTransaccion;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TipoTransaccionCrudRepository extends CrudRepository<TipoTransaccion,Integer> {

    Optional<TipoTransaccion> findByIdTipoTransaccion(Integer idTipoTransaccion);
    Optional<List<TipoTransaccion>> findByNombreTipoTransaccionContainingAndActivoTrueOrderByNombreTipoTransaccionAsc(String nombreTipoTransaccion);
    Optional<List<TipoTransaccion>> findByNombreTipoTransaccionContainingAndActivoFalseOrderByTNombreipoTransaccionAsc(String nombreTipoTransacciono);
    Optional<List<TipoTransaccion>> findAllByActivoTrueOrderByNombreTipoTransaccionAsc();
    Optional<List<TipoTransaccion>> findAllByActivoFalseOrderByNombreTipoTransaccionAsc();
    @Transactional
    @Modifying
    @Query("UPDATE TipoTransaccion t SET t.activo = :activo WHERE t.idTipoTransaccion = :idTipoTransaccion")
    void setActivoByIdTipoTransaccion(Integer idTipoTransaccion, boolean activo);
}