package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.CuentaDto;
import com.dev.gestorgastos.domain.repository.CuentaDtoRepository;
import com.dev.gestorgastos.persistence.crud.CuentaCrudRepository;
import com.dev.gestorgastos.persistence.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CuentaRepository implements CuentaDtoRepository {
    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Override
    public Optional<CuentaDto> getByIdCuenta(Integer idCuenta) {
        return cuentaCrudRepository.findByIdCuenta(idCuenta).map(CuentaMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByDescripcionContains(String descripcion) {
        return cuentaCrudRepository.findByDescripcionContainingAndActivoTrueOrderByDescripcionAsc(descripcion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getInactivosByDescripcionContains(String descripcion) {
        return cuentaCrudRepository.findByDescripcionContainingAndActivoFalseOrderByDescripcionAsc(descripcion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdProveedor(Integer idProveedor) {
        return cuentaCrudRepository.findByIdProveedorAndActivoTrueOrderByDescripcionAsc(idProveedor).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdDenominacion(Integer idDenominacion) {
        return cuentaCrudRepository.findByIdDenominacionAndActivoTrueOrderByDescripcionAsc(idDenominacion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdPersona(Integer idPersona) {
        return cuentaCrudRepository.findByIdPersonaAndActivoTrueOrderByDescripcionAsc(idPersona).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getAll() {
        return cuentaCrudRepository.findAllByActivoTrueOrderByDescripcionAsc().map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getAllDeleted() {
        return cuentaCrudRepository.findAllByActivoFalseOrderByDescripcionAsc().map(CuentaMapper.INSTANCE::toDtos);
    }
    @Override
    public CuentaDto save(CuentaDto cuentaDto) {
        return CuentaMapper.INSTANCE.toDto(cuentaCrudRepository.save(CuentaMapper.INSTANCE.toEntity(cuentaDto)));
    }


    @Override
    public boolean delete(Integer idCuenta) {
        return  getByIdCuenta(idCuenta).map(cuenta -> {
            cuentaCrudRepository.setActivoByIdCuenta(idCuenta,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idCuenta) {
        return  getByIdCuenta(idCuenta).map(cuenta -> {
            cuentaCrudRepository.setActivoByIdCuenta(idCuenta,true);
            return true;
        }).orElse(false);
    }
}
