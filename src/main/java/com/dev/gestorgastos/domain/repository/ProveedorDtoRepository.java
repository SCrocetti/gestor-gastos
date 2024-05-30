package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.ProveedorDto;

import java.util.List;
import java.util.Optional;

public interface ProveedorDtoRepository {
    public Optional<ProveedorDto> getByIdProveedor(Integer idProveedor);
    public Optional<List<ProveedorDto>> getByNombreProveedorContains(String nombreProveedor);
    public List<ProveedorDto> getAll() ;
    public ProveedorDto save(ProveedorDto proveedorDto);
    public boolean delete(Integer idProveedor);
}
