package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;

import java.util.List;
import java.util.Optional;

public interface PresupuestoMovimientoDtoRepository {
    public Optional<PresupuestoMovimientoDto> getByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento);
    public Optional<List<PresupuestoMovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<PresupuestoMovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento);
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdCuenta(Integer idCuenta);
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdPlan(Integer idPlan);
    public Optional<List<PresupuestoMovimientoDto>> getAll() ;
    public Optional<List<PresupuestoMovimientoDto>> getAllDeleted();
    public PresupuestoMovimientoDto save(PresupuestoMovimientoDto presupuestoMovimientoDto);
    public boolean delete(Integer idPresupuestoMovimiento);
    public boolean unDelete(Integer idPresupuestoMovimiento);
}
