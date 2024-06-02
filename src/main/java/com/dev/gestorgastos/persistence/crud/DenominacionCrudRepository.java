package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Denominacion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DenominacionCrudRepository extends CrudRepository<Denominacion,Integer> {

    Optional<Denominacion> findByIdDenominacion(Integer idDenominacion);
    Optional<List<Denominacion>> findByNombreDenominacionContainingAndActivoTrueOrderByNombreDenominacionAsc(String nombreDenominacion);
    Optional<List<Denominacion>> findByNombreDenominacionContainingAndActivoFalseOrderByNombreDenominacionAsc(String nombreDenominacion);
    Optional<List<Denominacion>> findAllByActivoTrueOrderByNombreDenominacionAsc();
    Optional<List<Denominacion>> findAllByActivoFalseOrderByNombreDenominacionAsc();
    @Transactional
    @Modifying
    @Query("UPDATE Denominacion d SET d.activo = :activo WHERE d.idDenominacion = :idDenominacion")
    void setActivoByIdDenominacion(Integer idDenominacion, boolean activo);
}