package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.TipoMovimientoDto;
import com.dev.gestorgastos.domain.repository.TipoMovimientoDtoRepository;
import com.dev.gestorgastos.persistence.crud.TipoMovimientoCrudRepository;
import com.dev.gestorgastos.persistence.mapper.TipoMovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public boolean delete(Integer idTipoMovimiento) {
        return  getByIdTipoMovimiento(idTipoMovimiento).map(tipoMovimiento -> {
            tipoMovimientoCrudRepository.setActivoByIdTipoMovimiento(idTipoMovimiento,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idTipoMovimiento) {
        return  getByIdTipoMovimiento(idTipoMovimiento).map(tipoMovimiento -> {
            tipoMovimientoCrudRepository.setActivoByIdTipoMovimiento(idTipoMovimiento,true);
            return true;
        }).orElse(false);
    }
}
