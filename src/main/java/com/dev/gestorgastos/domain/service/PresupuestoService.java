package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.persistence.PresupuestoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {
    @Autowired
    PresupuestoMovimientoRepository presupuestoMovimientoRepository;

    public Optional<PresupuestoMovimientoDto> getByIdPresupuesto(Integer idPresupuesto) {
        return presupuestoMovimientoRepository.getByIdPresupuesto(idPresupuesto);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoMovimientoRepository.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getInactivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoMovimientoRepository.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return presupuestoMovimientoRepository.getActivosByIdTipoMovimiento(idTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return presupuestoMovimientoRepository.getActivosByIdCuenta(idCuenta);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoMovimientoRepository.getActivosByIdPlan(idPlan);
    }

    public Optional<List<PresupuestoMovimientoDto>> getAll() {
        return presupuestoMovimientoRepository.getAll();
    }

    public Optional<List<PresupuestoMovimientoDto>> getAllDeleted() {
        return presupuestoMovimientoRepository.getAllDeleted();
    }

    public PresupuestoMovimientoDto save(PresupuestoMovimientoDto presupuestoMovimientoDto) {
        return presupuestoMovimientoRepository.save(presupuestoMovimientoDto);
    }

    public boolean delete(Integer idPresupuesto) {
        return  presupuestoMovimientoRepository.delete(idPresupuesto);
    }

    public boolean unDelete(Integer idPresupuesto) {
        return  presupuestoMovimientoRepository.unDelete(idPresupuesto);
    }
}
