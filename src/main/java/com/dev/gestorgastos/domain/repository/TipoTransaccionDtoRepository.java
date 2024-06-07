package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.TipoMovimientoDto;
import com.dev.gestorgastos.domain.dto.TipoTransaccionDto;

import java.util.List;
import java.util.Optional;

public interface TipoTransaccionDtoRepository {
    public Optional<TipoTransaccionDto> getByIdTipoTransaccion(Integer idTipoTransaccion);
    public Optional<List<TipoTransaccionDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoTransaccion);
    public Optional<List<TipoTransaccionDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoTransaccion);
    public Optional<List<TipoTransaccionDto>> getAll() ;
    public Optional<List<TipoTransaccionDto>> getAllDeleted();
    public TipoTransaccionDto save(TipoTransaccionDto tipoMovimientoDto);
    public boolean delete(Integer idTipoTransaccion);
    public boolean unDelete(Integer idTipoTransaccion);
}
