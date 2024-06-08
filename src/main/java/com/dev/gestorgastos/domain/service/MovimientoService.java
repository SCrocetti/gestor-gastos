package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.persistence.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;
    public Optional<MovimientoDto> getByIdMovimiento(Integer idMovimiento) {
        return movimientoRepository.getByIdMovimiento(idMovimiento);
    }

    public Optional<List<MovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoRepository.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<MovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoRepository.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<MovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return movimientoRepository.getActivosByIdTipoMovimiento(idTipoMovimiento);
    }

    public Optional<List<MovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return movimientoRepository.getActivosByIdCuenta(idCuenta);
    }

    public Optional<List<MovimientoDto>> getActivosByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento) {
        return movimientoRepository.getActivosByIdPresupuestoMovimiento(idPresupuestoMovimiento);
    }

    public Optional<List<MovimientoDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime){
        return movimientoRepository.getActivosByFechaHoraBetween(startDateTime,endDateTime);
    }
    public Optional<List<MovimientoDto>> getAll() {
        return movimientoRepository.getAll();
    }

    public Optional<List<MovimientoDto>> getAllDeleted() {
        return movimientoRepository.getAllDeleted();
    }

    public MovimientoDto save(MovimientoDto movimientoDto) throws IllegalArgumentException{
        return movimientoRepository.save(movimientoDto);
    }
    public boolean delete(Integer idMovimiento) {
        return  movimientoRepository.delete(idMovimiento);
    }

    public boolean unDelete(Integer idMovimiento) {
        return movimientoRepository.unDelete(idMovimiento);
    }
}
