package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Persona;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonaCrudRepository extends CrudRepository<Persona,Integer> {

    Optional<Persona> findByIdPersona(Integer idPersona);
    Optional<Persona> findByNumeroDocumento(String numeroDocumento);
    Optional<List<Persona>> findAllByActivoTrueOrderByApellidosAscNombresAsc();
    Optional<List<Persona>> findAllByActivoFalseOrderByApellidosAscNombresAsc();

    @Transactional
    @Modifying
    @Query("UPDATE Persona p SET p.activo = :activo WHERE p.idPersona = :idPersona")
    void setActivoByIdPersona(Integer idPersona, boolean activo);
}