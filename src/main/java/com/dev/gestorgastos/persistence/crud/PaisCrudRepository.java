package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Pais;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaisCrudRepository extends CrudRepository<Pais,Integer> {    // query method.
    List<Pais> findAllByOrderByNombrePaisAsc();
}