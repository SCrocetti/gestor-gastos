package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.ProveedorDto;
import com.dev.gestorgastos.persistence.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public Optional<ProveedorDto> getByIdProveedor(Integer idProveedor){
        return proveedorRepository.getByIdProveedor(idProveedor);
    }
    public Optional<List<ProveedorDto>> getActiveByNombreProveedorContains(String nombreProveedor){
        return proveedorRepository.getActivosByNombreProveedorContains(nombreProveedor);
    }
    public Optional<List<ProveedorDto>> getUnactiveByNombreProveedorContains(String nombreProveedor){
        return proveedorRepository.getInactivosByNombreProveedorContains(nombreProveedor);
    }
    public Optional<List<ProveedorDto>>  getAll() {
        return proveedorRepository.getAll();
    }

    public Optional<List<ProveedorDto>>  getAllDeleted() {
        return proveedorRepository.getAllDeleted();
    }

    public ProveedorDto save(ProveedorDto proveedorDto){
        return proveedorRepository.save(proveedorDto);
    }
    public boolean delete(Integer idProveedor){
        return proveedorRepository.delete(idProveedor);
    }
    public boolean unDelete(Integer idProveedor){
        return proveedorRepository.unDelete(idProveedor);
    }
}
