package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.persistence.crud.PaisCrudRepository;
import com.dev.gestorgastos.persistence.entity.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaisRepository {
    @Autowired
    PaisCrudRepository paisCrudRepository;
    public List<Pais> getAll() {
        return paisCrudRepository.findAllByOrderByNombrePaisAsc();
    }
    public Pais save(Pais pais){
        return paisCrudRepository.save(pais);
    }
    public boolean delete(Integer paisId){
        paisCrudRepository.deleteById(paisId);
        return paisCrudRepository.existsById(paisId);
    }
}
