package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.domain.repository.DenominacionDtoRepository;
import com.dev.gestorgastos.domain.repository.PersonaDtoRepository;
import com.dev.gestorgastos.persistence.crud.DenominacionCrudRepository;
import com.dev.gestorgastos.persistence.crud.PersonaCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DenominacionRepository implements DenominacionDtoRepository {
    @Autowired
    DenominacionCrudRepository denominacionCrudRepository;


    @Override
    public Optional<DenominacionDto> getByIdDenominacion(Integer idDenominacion) {
        return denominacionCrudRepository.findByIdDenominacion(idDenominacion).map(DenominacionMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<DenominacionDto>> getByNombreDenominacionContains(String nombreDenominacion) {
        return denominacionCrudRepository.findByNombreDenominacionContaining(nombreDenominacion).map(DenominacionMapper.INSTANCE::toDtos);
    }

    @Override
    public List<DenominacionDto> getAll() {
        return DenominacionMapper.INSTANCE.toDtos(denominacionCrudRepository.findAllByOrderByNombreDenominacionAsc());
    }

    @Override
    public DenominacionDto save(DenominacionDto denominacionDto) {
        return DenominacionMapper.INSTANCE.toDto(denominacionCrudRepository.save(DenominacionMapper.INSTANCE.toEntity(denominacionDto)));
    }

    @Override
    public boolean delete(Integer idDenominacion) {
        return  getByIdDenominacion(idDenominacion).map(denominacion -> {
            denominacionCrudRepository.deleteById(idDenominacion);
            return true;
        }).orElse(false);
    }
}
