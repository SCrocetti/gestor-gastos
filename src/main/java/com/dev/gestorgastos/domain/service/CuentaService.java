package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.CuentaDto;
import com.dev.gestorgastos.persistence.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    CuentaRepository cuentaRepository;

    public Optional<CuentaDto> getByIdCuenta(Integer idCuenta){
        return cuentaRepository.getByIdCuenta(idCuenta);
    }
    public Optional<List<CuentaDto>> getActiveByDescripcion(String descripcion){
        return cuentaRepository.getActivosByDescripcionContains(descripcion);
    }
    public Optional<List<CuentaDto>> getUnactiveByDescripcion(String descripcion){
        return cuentaRepository.getInactivosByDescripcionContains(descripcion);
    }
    public Optional<List<CuentaDto>> getActiveByIdDenominacion(Integer idDenominacion){
        return cuentaRepository.getActivosByIdDenominacion(idDenominacion);
    }
    public Optional<List<CuentaDto>> getActiveByIdProveedor(Integer idProveedor){
        return cuentaRepository.getActivosByIdProveedor(idProveedor);
    }
    public Optional<List<CuentaDto>> getActiveByIdPersona(Integer idPersona){
        return cuentaRepository.getActivosByIdPersona(idPersona);
    }


    public Optional<List<CuentaDto>>  getAll() {
        return cuentaRepository.getAll();
    }

    public Optional<List<CuentaDto>>  getAllDeleted() {
        return cuentaRepository.getAllDeleted();
    }

    public CuentaDto save(CuentaDto cuentaDto){
        return cuentaRepository.save(cuentaDto);
    }
    public boolean delete(Integer idCuenta){
        return cuentaRepository.delete(idCuenta);
    }
    public boolean unDelete(Integer idCuenta){
        return cuentaRepository.unDelete(idCuenta);
    }
}
