package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.TipoTransaccionDto;
import com.dev.gestorgastos.domain.repository.TipoTransaccionDtoRepository;
import com.dev.gestorgastos.persistence.crud.TipoTransaccionCrudRepository;
import com.dev.gestorgastos.persistence.entity.PresupuestoTransaccion;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.TipoTransaccionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TipoTransaccionRepository implements TipoTransaccionDtoRepository {
    @Autowired
    TipoTransaccionCrudRepository tipoTransaccionCrudRepository;

    @Override
    public Optional<TipoTransaccionDto> getByIdTipoTransaccion(Integer idTipoTransaccion) {
        return tipoTransaccionCrudRepository.findByIdTipoTransaccion(idTipoTransaccion).map(TipoTransaccionMapper.INSTANCE::toDto);
    }


    @Override
    public Optional<List<TipoTransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return tipoTransaccionCrudRepository.findByNombreTipoTransaccionContainingAndActivoTrueOrderByNombreTipoTransaccionAsc(nombreTipoTransaccion).map(TipoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoTransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return tipoTransaccionCrudRepository.findByNombreTipoTransaccionContainingAndActivoFalseOrderByNombreTipoTransaccionAsc(nombreTipoTransaccion).map(TipoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoTransaccionDto>> getAll() {
        return tipoTransaccionCrudRepository.findAllByActivoTrueOrderByNombreTipoTransaccionAsc().map(TipoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TipoTransaccionDto>> getAllDeleted() {
        return tipoTransaccionCrudRepository.findAllByActivoFalseOrderByNombreTipoTransaccionAsc().map(TipoTransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public TipoTransaccionDto save(TipoTransaccionDto tipoTransaccionDto) {
        return TipoTransaccionMapper.INSTANCE.toDto(tipoTransaccionCrudRepository.save(TipoTransaccionMapper.INSTANCE.toEntity(tipoTransaccionDto)));
    }

    @Override
    @Transactional
    public boolean delete(Integer idTipoTransaccion) {
        return tipoTransaccionCrudRepository.findByIdTipoTransaccion(idTipoTransaccion).map(tipoTransaccion -> {
            if(!tipoTransaccion.isActivo()){
                throw new EntityCannotBeDeletedException("Cannot delete TipoTransaccion with id " + idTipoTransaccion + " as it has already been deleted.");
            }
            if (tipoTransaccion.getPresupuestosTransacciones()!= null && !tipoTransaccion.getPresupuestosTransacciones().isEmpty()) {
                for(PresupuestoTransaccion presupuestoTransaccion: tipoTransaccion.getPresupuestosTransacciones()){
                    if(presupuestoTransaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete TipoTransaccion with id " + idTipoTransaccion + " as it has associated active PresupuestosTransacciones.");
                    }
                }
            }
            if (tipoTransaccion.getTransacciones()!= null && !tipoTransaccion.getTransacciones().isEmpty()) {
                for(Transaccion transaccion: tipoTransaccion.getTransacciones()){
                    if(transaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete TipoTransaccion with id " + idTipoTransaccion + " as it has associated active Transacciones.");
                    }
                }
            }
            tipoTransaccionCrudRepository.setActivoByIdTipoTransaccion(idTipoTransaccion, false);
            return true;
        }).orElse(false);
    }
    @Override
    public boolean unDelete(Integer idTipoTransaccion) {
        return  getByIdTipoTransaccion(idTipoTransaccion).map(tipoTransaccion -> {
            if(tipoTransaccion.isActivo()){
                throw new EntityCannotBeUndeletedException("Cannot undelete TipoTransaccion with id " + idTipoTransaccion + " as it's not deleted.");
            }
            tipoTransaccionCrudRepository.setActivoByIdTipoTransaccion(idTipoTransaccion,true);
            return true;
        }).orElse(false);
    }
}
