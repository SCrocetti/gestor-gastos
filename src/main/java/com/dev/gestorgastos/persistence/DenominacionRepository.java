package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.repository.DenominacionDtoRepository;
import com.dev.gestorgastos.persistence.crud.DenominacionCrudRepository;
import com.dev.gestorgastos.persistence.mapper.DenominacionMapper;
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
    public Optional<List<DenominacionDto>> getActivosByNombreDenominacionContains(String nombreDenominacion) {
        return denominacionCrudRepository.findByNombreDenominacionContainingAndActivoTrueOrderByNombreDenominacionAsc(nombreDenominacion).map(DenominacionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<DenominacionDto>> getInactivosByNombreDenominacionContains(String nombreDenominacion) {
        return denominacionCrudRepository.findByNombreDenominacionContainingAndActivoFalseOrderByNombreDenominacionAsc(nombreDenominacion).map(DenominacionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<DenominacionDto>> getAll() {
        return denominacionCrudRepository.findAllByActivoTrueOrderByNombreDenominacionAsc().map(DenominacionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<DenominacionDto>> getAllDeleted(){
        return denominacionCrudRepository.findAllByActivoFalseOrderByNombreDenominacionAsc().map(DenominacionMapper.INSTANCE::toDtos);
    }
    @Override
    public DenominacionDto save(DenominacionDto denominacionDto) {
        return DenominacionMapper.INSTANCE.toDto(denominacionCrudRepository.save(DenominacionMapper.INSTANCE.toEntity(denominacionDto)));
    }

    @Override
    public boolean delete(Integer idDenominacion) {
        return  getByIdDenominacion(idDenominacion).map(denominacion -> {
            denominacionCrudRepository.setActivoByIdDenominacion(idDenominacion,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idDenominacion) {
        return  getByIdDenominacion(idDenominacion).map(denominacion -> {
            denominacionCrudRepository.setActivoByIdDenominacion(idDenominacion,true);
            return true;
        }).orElse(false);
    }
}
