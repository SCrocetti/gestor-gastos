package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.ProveedorDto;

import java.util.List;
import java.util.Optional;

public interface ProveedorDtoRepository {
    public Optional<ProveedorDto> getByIdProveedor(Integer idProveedor);
    public Optional<List<ProveedorDto>> getActivosByNombreProveedorContains(String nombreProveedor);
    public Optional<List<ProveedorDto>> getInactivosByNombreProveedorContains(String nombreProveedor);
    public Optional<List<ProveedorDto>> getAll() ;
    public Optional<List<ProveedorDto>> getAllDeleted();
    public ProveedorDto save(ProveedorDto proveedorDto);
    public boolean delete(Integer idProveedor);
    public boolean unDelete(Integer idProveedor);
}
