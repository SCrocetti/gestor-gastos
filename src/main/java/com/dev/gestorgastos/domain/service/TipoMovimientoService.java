package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.TipoMovimientoDto;
import com.dev.gestorgastos.persistence.TipoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoService {
    @Autowired
    TipoMovimientoRepository tipoMovimientoRepository;

    public Optional<TipoMovimientoDto> getByIdTipoMovimiento(Integer idTipoMovimiento){
        return tipoMovimientoRepository.getByIdTipoMovimiento(idTipoMovimiento);
    }
    public Optional<List<TipoMovimientoDto>> getActiveByNombreTipoMocimientoontains(String nombreTipoMovimiento){
        return tipoMovimientoRepository.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }
    public Optional<List<TipoMovimientoDto>> getUnactiveByNombreTipoMocimientoontains(String nombreTipoMovimiento){
        return tipoMovimientoRepository.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }
    public Optional<List<TipoMovimientoDto>>  getAll() {
        return tipoMovimientoRepository.getAll();
    }

    public Optional<List<TipoMovimientoDto>>  getAllDeleted() {
        return tipoMovimientoRepository.getAllDeleted();
    }

    public TipoMovimientoDto save(TipoMovimientoDto tipoMovimientoDto){
        return tipoMovimientoRepository.save(tipoMovimientoDto);
    }
    public boolean delete(Integer idTipoMovimiento){
        return tipoMovimientoRepository.delete(idTipoMovimiento);
    }
    public boolean unDelete(Integer idTipoMovimiento){
        return tipoMovimientoRepository.unDelete(idTipoMovimiento);
    }
}
