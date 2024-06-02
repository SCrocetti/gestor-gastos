package com.dev.gestorgastos.persistence.crud;

import com.dev.gestorgastos.persistence.entity.Plan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanCrudRepository extends CrudRepository<Plan,Integer> {

    Optional<Plan> findByIdPlan(Integer idPlan);
    Optional<List<Plan>> findAllByActivoTrueOrderByDescripcionAsc();
    Optional<List<Plan>> findAllByActivoFalseOrderByDescripcionAsc();
    Optional<List<Plan>> findByDescripcionContainingAndActivoTrueOrderByDescripcionAsc(String descripcion);
    Optional<List<Plan>> findByDescripcionContainingAndActivoFalseOrderByDescripcionAsc(String descripcion);
    Optional<List<Plan>> findByFechaInicioBetweenAndActivoTrueOrderByFechaInicioAsc(LocalDate startDate, LocalDate endDate);
    Optional<List<Plan>> findByFechaFinBetweenAndActivoTrueOrderByFechaFinAsc(LocalDate startDate, LocalDate endDate);
    @Transactional
    @Modifying
    @Query("UPDATE Plan p SET p.activo = :activo WHERE p.idPlan = :idPlan")
    void setActivoByIdPlan(Integer idPlan, boolean activo);
}