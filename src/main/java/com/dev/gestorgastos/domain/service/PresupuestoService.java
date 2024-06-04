package com.dev.gestorgastos.domain.service;

import com.dev.gestorgastos.domain.dto.PresupuestoDto;
import com.dev.gestorgastos.persistence.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PresupuestoService {
    @Autowired
    PresupuestoRepository presupuestoRepository;

    public Optional<PresupuestoDto> getByIdPresupuesto(Integer idPresupuesto) {
        return presupuestoRepository.getByIdPresupuesto(idPresupuesto);
    }

    public Optional<List<PresupuestoDto>> getActivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoRepository.getActivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoDto>> getInactivosByNombreTipoMovimiento(String nombreTipoMovimiento) {
        return presupuestoRepository.getInactivosByNombreTipoMovimientoContains(nombreTipoMovimiento);
    }

    public Optional<List<PresupuestoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return presupuestoRepository.getActivosByIdTipoMovimiento(idTipoMovimiento);
    }

    public Optional<List<PresupuestoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return presupuestoRepository.getActivosByIdCuenta(idCuenta);
    }

    public Optional<List<PresupuestoDto>> getActivosByIdPlan(Integer idPlan) {
        return presupuestoRepository.getActivosByIdPlan(idPlan);
    }

    public Optional<List<PresupuestoDto>> getAll() {
        return presupuestoRepository.getAll();
    }

    public Optional<List<PresupuestoDto>> getAllDeleted() {
        return presupuestoRepository.getAllDeleted();
    }

    public PresupuestoDto save(PresupuestoDto presupuestoDto) {
        return presupuestoRepository.save(presupuestoDto);
    }

    public boolean delete(Integer idPresupuesto) {
        return  presupuestoRepository.delete(idPresupuesto);
    }

    public boolean unDelete(Integer idPresupuesto) {
        return  presupuestoRepository.unDelete(idPresupuesto);
    }
}
