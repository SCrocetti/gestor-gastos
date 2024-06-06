package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.persistence.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {
    @Autowired
    PresupuestoRepository presupuestoRepository;

    public Optional<PresupuestoMovimientoDto> getByIdPresupuesto(Integer idPresupuesto) {
        return presupuestoRepository.getByIdPresupuesto(idPresupuesto);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoRepository.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getInactivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoRepository.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return presupuestoRepository.getActivosByIdTipoMovimiento(idTipoMovimiento);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return presupuestoRepository.getActivosByIdCuenta(idCuenta);
    }

    public Optional<List<PresupuestoMovimientoDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoRepository.getActivosByIdPlan(idPlan);
    }

    public Optional<List<PresupuestoMovimientoDto>> getAll() {
        return presupuestoRepository.getAll();
    }

    public Optional<List<PresupuestoMovimientoDto>> getAllDeleted() {
        return presupuestoRepository.getAllDeleted();
    }

    public PresupuestoMovimientoDto save(PresupuestoMovimientoDto presupuestoMovimientoDto) {
        return presupuestoRepository.save(presupuestoMovimientoDto);
    }

    public boolean delete(Integer idPresupuesto) {
        return  presupuestoRepository.delete(idPresupuesto);
    }

    public boolean unDelete(Integer idPresupuesto) {
        return  presupuestoRepository.unDelete(idPresupuesto);
    }
}
