package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.PaisDto;
import com.dev.gestorgastos.domain.repository.PaisDtoRepository;
import com.dev.gestorgastos.persistence.crud.PaisCrudRepository;
import com.dev.gestorgastos.persistence.entity.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaisRepository implements PaisDtoRepository {
    @Autowired
    PaisCrudRepository paisCrudRepository;

    @Override
    public List<PaisDto> getAll() {
        return PaisMapper.INSTANCE.toDtos(paisCrudRepository.findAllByOrderByNombrePaisAsc());
    }

    @Override
    public PaisDto save(PaisDto pais) {
        return PaisMapper.INSTANCE.toDto(paisCrudRepository.save(PaisMapper.INSTANCE.toEntity(pais)));
    }
    @Override
    public boolean delete(Integer paisId){
        paisCrudRepository.deleteById(paisId);
        return paisCrudRepository.existsById(paisId);
    }
}
