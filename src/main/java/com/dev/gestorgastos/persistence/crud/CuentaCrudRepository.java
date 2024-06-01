package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Cuenta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CuentaCrudRepository extends CrudRepository<Cuenta,Integer> {

    Optional<Cuenta> findByIdCuenta(Integer idCuenta);
    Optional<List<Cuenta>> findByDescripcionContainingAndActivoTrueOrderByDescripcionAsc(String descripcion);
    Optional<List<Cuenta>> findByDescripcionContainingAndActivoFalseOrderByDescripcionAsc(String descripcion);
    Optional<List<Cuenta>> findAllByActivoTrueOrderByDescripcionAsc();
    Optional<List<Cuenta>> findAllByActivoFalseOrderByDescripcionAsc();
    Optional<List<Cuenta>> findByIdProveedorAndActivoTrueOrderByDescripcionAsc(Integer idProveedor);
    Optional<List<Cuenta>> findByIdDenominacionAndActivoTrueOrderByDescripcionAsc(Integer idDenominacion);
    Optional<List<Cuenta>> findByIdPersonaAndActivoTrueOrderByDescripcionAsc(Integer idPersona);
    @Transactional
    @Modifying
    @Query("UPDATE Cuenta c SET c.activo = :activo WHERE c.idCuenta = :idCuenta")
    void setActivoByIdCuenta(Integer idCuenta, boolean activo);
}