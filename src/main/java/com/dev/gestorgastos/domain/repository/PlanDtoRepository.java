package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.PlanDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanDtoRepository {
    public Optional<PlanDto> getByIdPlan(Integer idPlan);
    public Optional<List<PlanDto>> getAll() ;
    public Optional<List<PlanDto>> getAllDeleted();
    public Optional<List<PlanDto>> getActiveByDescripcion(String descripcion);
    public Optional<List<PlanDto>> getUnactiveByDescripcion(String descripcion);
    public Optional<List<PlanDto>> getActiveByFechaInicioBetween(LocalDate fechaInicio,LocalDate fechaFin);
    public Optional<List<PlanDto>> getActiveByFechaFinBetween(LocalDate fechaInicio,LocalDate fechaFin);
    public PlanDto save(PlanDto planDto);
    public boolean delete(Integer idPlan);
    public boolean unDelete(Integer idPlan);
}
