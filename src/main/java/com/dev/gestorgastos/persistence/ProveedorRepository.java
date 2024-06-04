package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.ProveedorDto;
import com.dev.gestorgastos.domain.repository.ProveedorDtoRepository;
import com.dev.gestorgastos.persistence.crud.ProveedorCrudRepository;
import com.dev.gestorgastos.persistence.mapper.ProveedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProveedorRepository implements ProveedorDtoRepository {
    @Autowired
    ProveedorCrudRepository proveedorCrudRepository;

    @Override
    public Optional<ProveedorDto> getByIdProveedor(Integer idProveedor) {
        return proveedorCrudRepository.findByIdProveedor(idProveedor).map(ProveedorMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<ProveedorDto>> getActivosByNombreProveedorContains(String nombreProveedor) {
        return proveedorCrudRepository.findByNombreProveedorContainingAndActivoTrueOrderByNombreProveedorAsc(nombreProveedor).map(ProveedorMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<ProveedorDto>> getInactivosByNombreProveedorContains(String nombreProveedor) {
        return proveedorCrudRepository.findByNombreProveedorContainingAndActivoFalseOrderByNombreProveedorAsc(nombreProveedor).map(ProveedorMapper.INSTANCE::toDtos);
    }
    @Override
    public Optional<List<ProveedorDto>> getAll() {
        return proveedorCrudRepository.findAllByActivoTrueOrderByNombreProveedorAsc().map(ProveedorMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<ProveedorDto>> getAllDeleted() {
        return proveedorCrudRepository.findAllByActivoFalseOrderByNombreProveedorAsc().map(ProveedorMapper.INSTANCE::toDtos);
    }

    @Override
    public ProveedorDto save(ProveedorDto proveedorDto) {
        return ProveedorMapper.INSTANCE.toDto(proveedorCrudRepository.save(ProveedorMapper.INSTANCE.toEntity(proveedorDto)));
    }

    @Override
    public boolean delete(Integer idProveedor) {
        return  getByIdProveedor(idProveedor).map(proveedor -> {
            proveedorCrudRepository.setActivoByIdProveedor(idProveedor,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idProveedor) {
        return  getByIdProveedor(idProveedor).map(proveedor -> {
            proveedorCrudRepository.setActivoByIdProveedor(idProveedor,true);
            return true;
        }).orElse(false);
    }
}
