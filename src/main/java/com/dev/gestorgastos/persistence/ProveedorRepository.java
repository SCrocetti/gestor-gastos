package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.domain.repository.DenominacionDtoRepository;
import com.dev.gestorgastos.domain.repository.ProveedorDtoRepository;
import com.dev.gestorgastos.persistence.crud.DenominacionCrudRepository;
import com.dev.gestorgastos.persistence.crud.ProveedorCrudRepository;
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
    public Optional<List<ProveedorDto>> getByNombreProveedorContains(String nombreProveedor) {
        return proveedorCrudRepository.findByNombreProveedorContaining(nombreProveedor).map(ProveedorMapper.INSTANCE::toDtos);
    }

    @Override
    public List<ProveedorDto> getAll() {
        return ProveedorMapper.INSTANCE.toDtos(proveedorCrudRepository.findAllByOrderByNombreProveedorAsc());
    }

    @Override
    public ProveedorDto save(ProveedorDto proveedorDto) {
        return ProveedorMapper.INSTANCE.toDto(proveedorCrudRepository.save(ProveedorMapper.INSTANCE.toEntity(proveedorDto)));
    }

    @Override
    public boolean delete(Integer idProveedor) {
        return  getByIdProveedor(idProveedor).map(proveedor -> {
            proveedorCrudRepository.deleteById(idProveedor);
            return true;
        }).orElse(false);
    }
}
