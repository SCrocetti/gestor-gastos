package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.DenominacionDto;
import com.dev.gestorgastos.domain.repository.DenominacionDtoRepository;
import com.dev.gestorgastos.persistence.crud.DenominacionCrudRepository;
import com.dev.gestorgastos.persistence.entity.Cuenta;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.DenominacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean delete(Integer idDenominacion) {
        return denominacionCrudRepository.findByIdDenominacion(idDenominacion).map(denominacion -> {
            if(!denominacion.isActivo()){
                throw new EntityCannotBeDeletedException("Cannot delete Denominacion with id " + idDenominacion + " as it has already been deleted.");
            }
            if (denominacion.getCuentas() != null && !denominacion.getCuentas().isEmpty()) {
                for(Cuenta cuenta : denominacion.getCuentas()){
                    if(cuenta.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Denominacion with id " + idDenominacion + " as it has associated active Cuentas.");
                    }
                }
            }
            denominacionCrudRepository.setActivoByIdDenominacion(idDenominacion, false);
            return true;
        }).orElse(false);
    }
    @Override
    public boolean unDelete(Integer idDenominacion) {
        return  getByIdDenominacion(idDenominacion).map(denominacion -> {
            if(denominacion.isActivo()){
                throw new EntityCannotBeUndeletedException("Cannot undelete Denominacion with id " + idDenominacion + " as it's not deleted.");
            }
            denominacionCrudRepository.setActivoByIdDenominacion(idDenominacion,true);
            return true;
        }).orElse(false);
    }
}
