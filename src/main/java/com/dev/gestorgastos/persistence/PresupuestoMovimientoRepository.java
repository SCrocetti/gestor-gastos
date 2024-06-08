package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.persistence.crud.CuentaCrudRepository;
import com.dev.gestorgastos.persistence.crud.PlanCrudRepository;
import com.dev.gestorgastos.persistence.crud.PresupuestoMovimientoCrudRepository;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.domain.repository.PresupuestoMovimientoDtoRepository;
import com.dev.gestorgastos.persistence.crud.TipoMovimientoCrudRepository;
import com.dev.gestorgastos.persistence.entity.*;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.mapper.PresupuestoMovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PresupuestoMovimientoRepository implements PresupuestoMovimientoDtoRepository {
    @Autowired
    PresupuestoMovimientoCrudRepository presupuestoMovimientoCrudRepository;

    @Autowired
    TipoMovimientoCrudRepository tipoMovimientoCrudRepository;

    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    PlanCrudRepository planCrudRepository;

    @Override
    public Optional<PresupuestoMovimientoDto> getByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento) {
        return presupuestoMovimientoCrudRepository.findByIdPresupuestoMovimiento(idPresupuestoMovimiento).map(PresupuestoMovimientoMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return presupuestoMovimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByIdTipoMovimientoAsc(nombreTipoMovimiento).map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return presupuestoMovimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByIdTipoMovimientoAsc(nombreTipoMovimiento).map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return presupuestoMovimientoCrudRepository.findByIdTipoMovimientoAndActivoTrue(idTipoMovimiento).map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return presupuestoMovimientoCrudRepository.findByIdCuentaAndActivoTrueOrderByIdTipoMovimientoAsc(idCuenta).map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoMovimientoCrudRepository.findByIdPlanAndActivoTrueOrderByIdTipoMovimientoAsc(idPlan).map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getAll() {
        return presupuestoMovimientoCrudRepository.findAllByActivoTrueOrderByIdTipoMovimientoAsc().map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoMovimientoDto>> getAllDeleted() {
        return presupuestoMovimientoCrudRepository.findAllByActivoFalseOrderByIdTipoMovimientoAsc().map(PresupuestoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public PresupuestoMovimientoDto save(PresupuestoMovimientoDto presupuestoMovimientoDto) {
        PresupuestoMovimiento presupuestoMovimiento=PresupuestoMovimientoMapper.INSTANCE.toEntity(presupuestoMovimientoDto);

        Optional<TipoMovimiento> tipoMovimientoOpt = tipoMovimientoCrudRepository.findByIdTipoMovimiento(presupuestoMovimientoDto.getIdTipoMovimiento());
        Optional<Cuenta> cuentaOpt = cuentaCrudRepository.findByIdCuenta(presupuestoMovimientoDto.getIdCuenta());
        Optional<Plan> planOpt = planCrudRepository.findByIdPlan(presupuestoMovimientoDto.getIdPlan());

        if (!tipoMovimientoOpt.isPresent() ) {
            throw new IllegalArgumentException("TipoMovimiento  not found");
        }

        if (!cuentaOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta not found");
        }
        if (!planOpt.isPresent()) {
            throw new IllegalArgumentException("Plan not found");
        }

        return PresupuestoMovimientoMapper.INSTANCE.toDto(presupuestoMovimientoCrudRepository.save(presupuestoMovimiento));
    }
    @Override
    public boolean delete(Integer idPresupuestoMovimiento) {
        return presupuestoMovimientoCrudRepository.findByIdPresupuestoMovimiento(idPresupuestoMovimiento).map(presupuestoMovimiento -> {

            if (presupuestoMovimiento.getMovimientos() != null && !presupuestoMovimiento.getMovimientos().isEmpty()) {
                for(Movimiento movimiento: presupuestoMovimiento.getMovimientos()){
                    if(movimiento.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete PresupuestoMovimiento with id " + idPresupuestoMovimiento + " as it has associated active Movimientos.");
                    }
                }
            }
            presupuestoMovimientoCrudRepository.setActivoByIdPresupuesto(idPresupuestoMovimiento,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idPresupuestoMovimiento) {
        return  getByIdPresupuestoMovimiento(idPresupuestoMovimiento).map(presupuestoMovimiento -> {
            presupuestoMovimientoCrudRepository.setActivoByIdPresupuesto(idPresupuestoMovimiento,true);
            return true;
        }).orElse(false);
    }
}
