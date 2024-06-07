package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.MovimientoDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovimientoDtoRepository {
    public Optional<MovimientoDto> getByIdMovimiento(Integer idMovimiento);
    public Optional<List<MovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<MovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<MovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento);
    public Optional<List<MovimientoDto>> getActivosByIdCuenta(Integer idCuenta);
    public Optional<List<MovimientoDto>> getActivosByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento);
    public Optional<List<MovimientoDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    public Optional<List<MovimientoDto>> getAll() ;
    public Optional<List<MovimientoDto>> getAllDeleted();
    public MovimientoDto save(MovimientoDto movimientoDto);
    public boolean delete(Integer idMovimiento);
    public boolean unDelete(Integer idMovimiento);
}
