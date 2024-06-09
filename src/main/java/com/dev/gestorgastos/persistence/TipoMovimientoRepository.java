package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.TipoMovimientoDto;
import com.dev.gestorgastos.domain.repository.TipoMovimientoDtoRepository;
import com.dev.gestorgastos.persistence.crud.TipoMovimientoCrudRepository;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.TipoMovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoMovimientoRepository implements TipoMovimientoDtoRepository {
    @Autowired
    TipoMovimientoCrudRepository tipoMovimientoCrudRepository;
    @Override
    public Optional<TipoMovimientoDto> getByIdTipoMovimiento(Integer idTipoMovimiento) {
        return tipoMovimientoCrudRepository.findByIdTipoMovimiento(idTipoMovimiento).map(TipoMovimientoMapper.INSTANCE::toDto);
    }


    @Override
    public Optional<List<TipoMovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return tipoMovimientoCrudRepository.findByNombreTipoMovimientoContainingAndActivoTrueOrderByNombreTipoMovimientoAsc(nombreTipoMovimiento).map(TipoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoMovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return tipoMovimientoCrudRepository.findByNombreTipoMovimientoContainingAndActivoFalseOrderByNombreTipoMovimientoAsc(nombreTipoMovimiento).map(TipoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoMovimientoDto>> getAll() {
        return tipoMovimientoCrudRepository.findAllByActivoTrueOrderByNombreTipoMovimientoAsc().map(TipoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoMovimientoDto>> getAllDeleted() {
        return tipoMovimientoCrudRepository.findAllByActivoFalseOrderByNombreTipoMovimientoAsc().map(TipoMovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public TipoMovimientoDto save(TipoMovimientoDto tipoMovimientoDto) {
        return TipoMovimientoMapper.INSTANCE.toDto(tipoMovimientoCrudRepository.save(TipoMovimientoMapper.INSTANCE.toEntity(tipoMovimientoDto)));
    }

    @Override
    @Transactional
    public boolean delete(Integer idTipoMovimiento) {
        return tipoMovimientoCrudRepository.findByIdTipoMovimiento(idTipoMovimiento).map(tipoMovimiento -> {
            if(!tipoMovimiento.isActivo()){
                throw new EntityCannotBeDeletedException("Cannot delete TipoMovimiento with id " + idTipoMovimiento + " as it has already been deleted.");
            }
            if (tipoMovimiento.getPresupuestosMovimientos() != null && !tipoMovimiento.getPresupuestosMovimientos().isEmpty()) {
                for(PresupuestoMovimiento presupuestoMovimiento: tipoMovimiento.getPresupuestosMovimientos()){
                    if(presupuestoMovimiento.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete TipoMovimiento with id " + idTipoMovimiento + " as it has associated active PresupuestosMovimientos.");
                    }
                }
            }
            if (tipoMovimiento.getMovimientos() != null && !tipoMovimiento.getMovimientos().isEmpty()) {
                for(Movimiento movimiento: tipoMovimiento.getMovimientos()){
                    if(movimiento.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete TipoMovimiento with id " + idTipoMovimiento + " as it has associated active Movimientos.");
                    }
                }
            }
            tipoMovimientoCrudRepository.setActivoByIdTipoMovimiento(idTipoMovimiento, false);
            return true;
        }).orElse(false);
    }
    @Override
    public boolean unDelete(Integer idTipoMovimiento) {
        return  getByIdTipoMovimiento(idTipoMovimiento).map(tipoMovimiento -> {
            if(tipoMovimiento.isActivo()){
                throw new EntityCannotBeUndeletedException("Cannot undelete TipoMovimiento with id " + idTipoMovimiento + " as it's not deleted.");
            }
            tipoMovimientoCrudRepository.setActivoByIdTipoMovimiento(idTipoMovimiento,true);
            return true;
        }).orElse(false);
    }
}
