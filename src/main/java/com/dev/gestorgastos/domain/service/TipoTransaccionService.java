package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.TipoMovimientoDto;
import com.dev.gestorgastos.domain.dto.TipoTransaccionDto;
import com.dev.gestorgastos.persistence.TipoMovimientoRepository;
import com.dev.gestorgastos.persistence.TipoTransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoTransaccionService {
    @Autowired
    TipoTransaccionRepository tipoTransaccionRepository;
    public Optional<TipoTransaccionDto> getByIdTipoTransaccion(Integer idTipoTransaccion){
        return tipoTransaccionRepository.getByIdTipoTransaccion(idTipoTransaccion);
    }
    public Optional<List<TipoTransaccionDto>> getActiveByNombreTipoTransaccionContains(String nombreTipoTransaccion){
        return tipoTransaccionRepository.getActivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<TipoTransaccionDto>> getUnactiveByNombreTipoTransaccionContains(String nombreTipoTransaccion){
        return tipoTransaccionRepository.getInactivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<TipoTransaccionDto>>  getAll() {
        return tipoTransaccionRepository.getAll();
    }

    public Optional<List<TipoTransaccionDto>>  getAllDeleted() {
        return tipoTransaccionRepository.getAllDeleted();
    }

    public TipoTransaccionDto save(TipoTransaccionDto tipoTransaccionDto){
        return tipoTransaccionRepository.save(tipoTransaccionDto);
    }
    public boolean delete(Integer idTipoTransaccion){
        return tipoTransaccionRepository.delete(idTipoTransaccion);
    }
    public boolean unDelete(Integer idTipoTransaccion){
        return tipoTransaccionRepository.unDelete(idTipoTransaccion);
    }
}
