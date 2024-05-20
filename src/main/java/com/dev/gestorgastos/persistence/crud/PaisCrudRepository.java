package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Pais;
import org.springframework.data.repository.CrudRepository;

public interface PaisCrudRepository extends CrudRepository<Pais,Integer> {
}