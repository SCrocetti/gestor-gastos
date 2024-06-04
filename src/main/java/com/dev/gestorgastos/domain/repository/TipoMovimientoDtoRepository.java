package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.TipoMovimientoDto;

import java.util.List;
import java.util.Optional;

public interface TipoMovimientoDtoRepository {
    public Optional<TipoMovimientoDto> getByIdTipoMovimiento(Integer idTipoMovimiento);
    public Optional<List<TipoMovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<TipoMovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<TipoMovimientoDto>> getAll() ;
    public Optional<List<TipoMovimientoDto>> getAllDeleted();
    public TipoMovimientoDto save(TipoMovimientoDto tipoMovimientoDto);
    public boolean delete(Integer idTipoMovimiento);
    public boolean unDelete(Integer idTipoMovimiento);
}
