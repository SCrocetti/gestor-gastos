package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.CuentaDto;
import com.dev.gestorgastos.domain.ProveedorDto;

import java.util.List;
import java.util.Optional;

public interface CuentaDtoRepository {
    public Optional<CuentaDto> getByIdCuenta(Integer idCuenta);
    public Optional<List<CuentaDto>> getActivosByDescripcionContains(String descripcion);
    public Optional<List<CuentaDto>> getInactivosByDescripcionContains(String descripcion);
    public Optional<List<CuentaDto>> getActivosByIdProveedor(Integer idProveedor);
    public Optional<List<CuentaDto>> getActivosByIdDenominacion(Integer idDenominacion);
    public Optional<List<CuentaDto>> getActivosByIdPersona(Integer idPersona);
    public Optional<List<CuentaDto>> getAll() ;
    public Optional<List<CuentaDto>> getAllDeleted();
    public CuentaDto save(CuentaDto cuentaDto);
    public boolean delete(Integer idCuenta);
    public boolean unDelete(Integer idCuenta);
}
