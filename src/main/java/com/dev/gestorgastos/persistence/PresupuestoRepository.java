package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.PresupuestoDto;
import com.dev.gestorgastos.domain.repository.PresupuestoDtoRepository;
import com.dev.gestorgastos.persistence.crud.PresupuestoCrudRepository;
import com.dev.gestorgastos.persistence.mapper.PresupuestoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PresupuestoRepository implements PresupuestoDtoRepository {
    @Autowired
    PresupuestoCrudRepository presupuestoCrudRepository;

    @Override
    public Optional<PresupuestoDto> getByIdPresupuesto(Integer idPresupuesto) {
        return presupuestoCrudRepository.findByIdPresupuesto(idPresupuesto).map(PresupuestoMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<PresupuestoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return presupuestoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(nombreTipoMovimiento).map(PresupuestoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return presupuestoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc(nombreTipoMovimiento).map(PresupuestoMapper.INSTANCE::toDtos);
    }
    @Override
    public Optional<List<PresupuestoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return presupuestoCrudRepository.findByIdTipoMovimientoAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(idTipoMovimiento).map(PresupuestoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return presupuestoCrudRepository.findByIdCuentaAndActivoTrueOrderByCuentaDescripcionAsc(idCuenta).map(PresupuestoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoCrudRepository.findByIdPlanAndActivoTrueOrderByPlanDescripcionAsc(idPlan).map(PresupuestoMapper.INSTANCE::toDtos);
    }


    @Override
    public Optional<List<PresupuestoDto>> getAll() {
        return presupuestoCrudRepository.findAllByActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc().map(PresupuestoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoDto>> getAllDeleted() {
        return presupuestoCrudRepository.findAllByActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc().map(PresupuestoMapper.INSTANCE::toDtos);
    }

    @Override
    public PresupuestoDto save(PresupuestoDto presupuestoDto) {
        return PresupuestoMapper.INSTANCE.toDto(presupuestoCrudRepository.save(PresupuestoMapper.INSTANCE.toEntity(presupuestoDto)));
    }

    @Override
    public boolean delete(Integer idPresupuesto) {
        return  getByIdPresupuesto(idPresupuesto).map(presupuesto -> {
            presupuestoCrudRepository.setActivoByIdPresupuesto(idPresupuesto,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idPresupuesto) {
        return  getByIdPresupuesto(idPresupuesto).map(presupuesto -> {
            presupuestoCrudRepository.setActivoByIdPresupuesto(idPresupuesto,true);
            return true;
        }).orElse(false);
    }
}
