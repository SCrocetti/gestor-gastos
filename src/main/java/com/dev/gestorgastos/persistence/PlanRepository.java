package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.PlanDto;
import com.dev.gestorgastos.domain.repository.PlanDtoRepository;
import com.dev.gestorgastos.persistence.crud.PlanCrudRepository;
import com.dev.gestorgastos.persistence.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PlanRepository implements PlanDtoRepository {
    @Autowired
    PlanCrudRepository planCrudRepository;


    @Override
    public Optional<PlanDto> getByIdPlan(Integer idPlan) {
        return planCrudRepository.findByIdPlan(idPlan).map(PlanMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<PlanDto>> getAll() {
        return planCrudRepository.findAllByActivoTrueOrderByDescripcionAsc().map(PlanMapper.INSTANCE::toDtos);
    }


    @Override
    public Optional<List<PlanDto>> getAllDeleted() {
        return planCrudRepository.findAllByActivoFalseOrderByDescripcionAsc().map(PlanMapper.INSTANCE::toDtos);
    }


    @Override
    public Optional<List<PlanDto>> getActiveByDescripcion(String descripcion) {
        return planCrudRepository.findByDescripcionContainingAndActivoTrueOrderByDescripcionAsc(descripcion).map(PlanMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PlanDto>> getUnactiveByDescripcion(String descripcion) {
        return planCrudRepository.findByDescripcionContainingAndActivoFalseOrderByDescripcionAsc(descripcion).map(PlanMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PlanDto>> getActiveByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return planCrudRepository.findByFechaInicioBetweenAndActivoTrueOrderByFechaInicioAsc(fechaInicio,fechaFin).map(PlanMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PlanDto>> getActiveByFechaFinBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return planCrudRepository.findByFechaFinBetweenAndActivoTrueOrderByFechaFinAsc(fechaInicio,fechaFin).map(PlanMapper.INSTANCE::toDtos);
    }

    @Override
    public PlanDto save(PlanDto planDto) {
        return PlanMapper.INSTANCE.toDto(planCrudRepository.save(PlanMapper.INSTANCE.toEntity(planDto)));
    }


    @Override
    public boolean delete(Integer idPlan) {
        return  getByIdPlan(idPlan).map(plan -> {
            planCrudRepository.setActivoByIdPlan(idPlan,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idPlan) {
        return  getByIdPlan(idPlan).map(plan -> {
            planCrudRepository.setActivoByIdPlan(idPlan,true);
            return true;
        }).orElse(false);
    }
}