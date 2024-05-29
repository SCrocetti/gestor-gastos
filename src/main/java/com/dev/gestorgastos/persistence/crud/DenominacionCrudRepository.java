package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Denominacion;
import com.dev.gestorgastos.persistence.entity.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DenominacionCrudRepository extends CrudRepository<Denominacion,Integer> {

    Optional<Denominacion> findByIdDenominacion(Integer idDenominacion);
    Optional<List<Denominacion>> findByNombreDenominacionContaining(String nombreDenominacion);
    List<Denominacion> findAllByOrderByNombreDenominacionAsc();
}