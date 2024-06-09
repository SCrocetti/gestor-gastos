package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.PresupuestoTransaccionDto;
import com.dev.gestorgastos.domain.repository.PresupuestoTransaccionDtoRepository;
import com.dev.gestorgastos.persistence.crud.*;
import com.dev.gestorgastos.persistence.entity.*;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.PresupuestoTransaccionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PresupuestoTransaccionRepository implements PresupuestoTransaccionDtoRepository {
    @Autowired
    PresupuestoTransaccionCrudRepository presupuestoTransaccionCrudRepository;

    @Autowired
    TipoTransaccionCrudRepository tipoTransaccionCrudRepository;

    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    PlanCrudRepository planCrudRepository;

    @Override
    public Optional<PresupuestoTransaccionDto> getByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion) {
        return presupuestoTransaccionCrudRepository.findByIdPresupuestoTransaccion(idPresupuestoTransaccion).map(PresupuestoTransaccionMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return presupuestoTransaccionCrudRepository.findByTipoTransaccionNombreTipoTransaccionContainingAndActivoTrueOrderByIdTipoTransaccionAsc(nombreTipoTransaccion).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return presupuestoTransaccionCrudRepository.findByTipoTransaccionNombreTipoTransaccionContainingAndActivoFalseOrderByIdTipoTransaccionAsc(nombreTipoTransaccion).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion) {
        return presupuestoTransaccionCrudRepository.findByIdTipoTransaccionAndActivoTrue(idTipoTransaccion).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdCuentaEgreso(Integer idCuentaEgreso) {
        return presupuestoTransaccionCrudRepository.findByIdCuentaEgresoAndActivoTrueOrderByIdTipoTransaccionAsc(idCuentaEgreso).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdCuentaIngreso(Integer idCuentaIngreso) {
        return presupuestoTransaccionCrudRepository.findByIdCuentaIngresoAndActivoTrueOrderByIdTipoTransaccionAsc(idCuentaIngreso).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoTransaccionCrudRepository.findByIdPlanAndActivoTrueOrderByIdTipoTransaccionAsc(idPlan).map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getAll() {
        return presupuestoTransaccionCrudRepository.findAllByActivoTrueOrderByIdTipoTransaccionAsc().map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<PresupuestoTransaccionDto>> getAllDeleted() {
        return presupuestoTransaccionCrudRepository.findAllByActivoFalseOrderByIdTipoTransaccionAsc().map(PresupuestoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    @Transactional
    public PresupuestoTransaccionDto save(PresupuestoTransaccionDto presupuestoTransaccionDto) {
        PresupuestoTransaccion presupuestoTransaccion=PresupuestoTransaccionMapper.INSTANCE.toEntity(presupuestoTransaccionDto);

        Optional<TipoTransaccion> tipoTransaccionOpt = tipoTransaccionCrudRepository.findByIdTipoTransaccion(presupuestoTransaccionDto.getIdTipoTransaccion());
        Optional<Cuenta> cuentaEgresoOpt = cuentaCrudRepository.findByIdCuenta(presupuestoTransaccionDto.getIdCuentaEgreso());
        Optional<Cuenta> cuentaIngresoOpt = cuentaCrudRepository.findByIdCuenta(presupuestoTransaccionDto.getIdCuentaIngreso());
        Optional<Plan> planOpt = planCrudRepository.findByIdPlan(presupuestoTransaccionDto.getIdPlan());

        if (!tipoTransaccionOpt.isPresent() ) {
            throw new IllegalArgumentException("TipoTransaccion  not found");
        }

        if (!cuentaEgresoOpt.isPresent()) {
            throw new IllegalArgumentException("CuentaEgreso not found");
        }
        if (!cuentaIngresoOpt.isPresent()) {
            throw new IllegalArgumentException("CuentaIngreso not found");
        }
        if (!planOpt.isPresent()) {
            throw new IllegalArgumentException("Plan not found");
        }
        return PresupuestoTransaccionMapper.INSTANCE.toDto(presupuestoTransaccionCrudRepository.save(presupuestoTransaccion));
    }

    @Override
    public boolean delete(Integer idPresupuestoTransaccion) {
        return presupuestoTransaccionCrudRepository.findByIdPresupuestoTransaccion(idPresupuestoTransaccion).map(presupuestoTransaccion -> {
            if(!presupuestoTransaccion.isActivo()){
                throw new EntityCannotBeDeletedException("Cannot delete PresupuestoTransaccion with id " + idPresupuestoTransaccion + " as it has already been deleted.");
            }
            if (presupuestoTransaccion.getTransacciones() != null && !presupuestoTransaccion.getTransacciones().isEmpty()) {
                for(Transaccion transaccion: presupuestoTransaccion.getTransacciones()){
                    if(transaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete PresupuestoTransaccion with id " + idPresupuestoTransaccion + " as it has associated active Transacciones.");
                    }
                }
            }
            presupuestoTransaccionCrudRepository.setActivoByIdPresupuestoTransaccion(idPresupuestoTransaccion,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idPresupuestoTransaccion) {
        return  getByIdPresupuestoTransaccion(idPresupuestoTransaccion).map(presupuestoTransaccion -> {
            if(presupuestoTransaccion.isActivo()){
                throw new EntityCannotBeUndeletedException("Cannot undelete PresupuestoTransaccion with id " + idPresupuestoTransaccion + " as it's not deleted.");
            }
            presupuestoTransaccionCrudRepository.setActivoByIdPresupuestoTransaccion(idPresupuestoTransaccion,true);
            return true;
        }).orElse(false);
    }
}
