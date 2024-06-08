package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.domain.dto.PresupuestoTransaccionDto;
import com.dev.gestorgastos.persistence.PresupuestoMovimientoRepository;
import com.dev.gestorgastos.persistence.PresupuestoTransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoTransaccionService {
    @Autowired
    PresupuestoTransaccionRepository presupuestoTransaccionRepository;

    public Optional<PresupuestoTransaccionDto> getByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion){
        return presupuestoTransaccionRepository.getByIdPresupuestoTransaccion(idPresupuestoTransaccion);
    }
    public Optional<List<PresupuestoTransaccionDto>> getActivosByNombreTipoTransaccion(String nombreTipoTransaccion){
        return presupuestoTransaccionRepository.getActivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<PresupuestoTransaccionDto>> getInactivosByNombreTipoTransaccion(String nombreTipoTransaccion){
        return presupuestoTransaccionRepository.getInactivosByNombreTipoTransaccionContains(nombreTipoTransaccion);
    }
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion){
        return presupuestoTransaccionRepository.getActivosByIdTipoTransaccion(idTipoTransaccion);
    }
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdCuenta(Integer idCuenta){
        return presupuestoTransaccionRepository.getActivosByIdCuenta(idCuenta);
    }
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdPlan(Integer idPlan){
        return presupuestoTransaccionRepository.getActivosByIdPlan(idPlan);
    }
    public Optional<List<PresupuestoTransaccionDto>> getAll() {
        return presupuestoTransaccionRepository.getAll();
    }
    public Optional<List<PresupuestoTransaccionDto>> getAllDeleted(){
        return presupuestoTransaccionRepository.getAllDeleted();
    }
    public PresupuestoTransaccionDto save(PresupuestoTransaccionDto presupuestoTransaccionDto){
        return presupuestoTransaccionRepository.save(presupuestoTransaccionDto);
    }
    public boolean delete(Integer idPresupuestoTransaccion){
        return presupuestoTransaccionRepository.delete(idPresupuestoTransaccion);
    }
    public boolean unDelete(Integer idPresupuestoTransaccion){
        return presupuestoTransaccionRepository.unDelete(idPresupuestoTransaccion);
    }
}
