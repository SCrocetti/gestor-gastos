package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.dto.TransaccionDto;
import com.dev.gestorgastos.persistence.MovimientoRepository;
import com.dev.gestorgastos.persistence.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {
    @Autowired
    TransaccionRepository transaccionRepository;
    public Optional<TransaccionDto> getByIdTransaccion(Integer idTransaccion){
        return transaccionRepository.getByIdTransaccion(idTransaccion);
    }
    public Optional<List<TransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion){
        return transaccionRepository.getActivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<TransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion){
        return transaccionRepository.getInactivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<TransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion){
        return transaccionRepository.getActivosByIdTipoTransaccion(idTipoTransaccion);
    }
    public Optional<List<TransaccionDto>> getActivosByIdCuentaEgreso(Integer idCuentaEgreso){
        return transaccionRepository.getActivosByIdCuentaEgreso(idCuentaEgreso);
    }
    public Optional<List<TransaccionDto>> getActivosByIdCuentaIngreso(Integer idCuentaIngreso){
        return transaccionRepository.getActivosByIdCuentaIngreso(idCuentaIngreso);
    }
    public Optional<List<TransaccionDto>> getActivosByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion){
        return transaccionRepository.getActivosByIdPresupuestoTransaccion(idPresupuestoTransaccion);
    }
    public Optional<List<TransaccionDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime){
        return transaccionRepository.getActivosByFechaHoraBetween(startDateTime,endDateTime);
    }
    public Optional<List<TransaccionDto>> getAll() {
        return transaccionRepository.getAll();
    }
    public Optional<List<TransaccionDto>> getAllDeleted(){
        return transaccionRepository.getAllDeleted();
    }
    public TransaccionDto save(TransaccionDto transaccionDto){
        return transaccionRepository.save(transaccionDto);
    }
    public boolean delete(Integer idTransaccion){
        return  transaccionRepository.delete(idTransaccion);
    }
    public boolean unDelete(Integer idTransaccion){
        return  transaccionRepository.unDelete(idTransaccion);
    }
}
