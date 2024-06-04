package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.PlanDto;
import com.dev.gestorgastos.persistence.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {
    @Autowired
    PlanRepository planRepository;

    public Optional<PlanDto> getByIdPlan(Integer idPlan){
        return planRepository.getByIdPlan(idPlan);
    }
    public Optional<List<PlanDto>> getActiveByDescripcion(String descripcion){
        return planRepository.getActiveByDescripcion(descripcion);
    }
    public Optional<List<PlanDto>> getUnactiveByDescripcion(String descripcion){
        return planRepository.getUnactiveByDescripcion(descripcion);
    }
    public Optional<List<PlanDto>> getActiveByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return planRepository.getActiveByFechaInicioBetween(fechaInicio,fechaFin);
    }
    public Optional<List<PlanDto>> getActiveByFechaFinBetween(LocalDate fechaInicio, LocalDate fechaFin) {
        return planRepository.getActiveByFechaFinBetween(fechaInicio,fechaFin);
    }

    public Optional<List<PlanDto>>  getAll() {
        return planRepository.getAll();
    }

    public Optional<List<PlanDto>>  getAllDeleted() {
        return planRepository.getAllDeleted();
    }

    public PlanDto save(PlanDto planDto){
        return planRepository.save(planDto);
    }
    public boolean delete(Integer idPlan){
        return planRepository.delete(idPlan);
    }
    public boolean unDelete(Integer idPlan){
        return planRepository.unDelete(idPlan);
    }
}
