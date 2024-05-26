package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaCrudRepository extends CrudRepository<Persona,Integer> {

    Optional<Persona> findByNumeroDocumento(String numeroDocumento);
    List<Persona> findAllByOrderByApellidosAscNombresAsc();
}