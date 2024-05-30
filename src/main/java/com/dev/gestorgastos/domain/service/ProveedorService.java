package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.persistence.DenominacionRepository;
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
    public Optional<List<ProveedorDto>> getByNombreProveedorContains(String nombreProveedor){
        return proveedorRepository.getByNombreProveedorContains(nombreProveedor);
    }
    public List<ProveedorDto> getAll() {
        return proveedorRepository.getAll();
    }
    public ProveedorDto save(ProveedorDto proveedorDto){
        return proveedorRepository.save(proveedorDto);
    }
    public boolean delete(Integer idProveedor){
        return proveedorRepository.delete(idProveedor);
    }
}
