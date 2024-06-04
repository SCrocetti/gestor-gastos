package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.PresupuestoDto;

import java.util.List;
import java.util.Optional;

public interface PresupuestoDtoRepository {
    public Optional<PresupuestoDto> getByIdPresupuesto(Integer idPresupuesto);
    public Optional<List<PresupuestoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<PresupuestoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento);
    public Optional<List<PresupuestoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento);
    public Optional<List<PresupuestoDto>> getActivosByIdCuenta(Integer idCuenta);
    public Optional<List<PresupuestoDto>> getActivosByIdPlan(Integer idPlan);
    public Optional<List<PresupuestoDto>> getAll() ;
    public Optional<List<PresupuestoDto>> getAllDeleted();
    public PresupuestoDto save(PresupuestoDto presupuestoDto);
    public boolean delete(Integer idPresupuesto);
    public boolean unDelete(Integer idPresupuesto);
}
