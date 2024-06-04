package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.repository.MovimientoDtoRepository;
import com.dev.gestorgastos.persistence.crud.CuentaCrudRepository;
import com.dev.gestorgastos.persistence.crud.MovimientoCrudRepository;
import com.dev.gestorgastos.persistence.crud.PresupuestoCrudRepository;
import com.dev.gestorgastos.persistence.entity.Cuenta;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.Presupuesto;
import com.dev.gestorgastos.persistence.mapper.MovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class MovimientoRepository implements MovimientoDtoRepository {
    @Autowired
    MovimientoCrudRepository movimientoCrudRepository;

    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    PresupuestoCrudRepository presupuestoCrudRepository;

    @Override
    public Optional<MovimientoDto> getByIdMovimiento(Integer idMovimiento) {
        return movimientoCrudRepository.findByIdMovimiento(idMovimiento).map(MovimientoMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(nombreTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc(nombreTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return movimientoCrudRepository.findByIdTipoMovimientoAndActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc(idTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return movimientoCrudRepository.findByIdCuentaAndActivoTrueOrderByCuentaDescripcionAsc(idCuenta).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdPresupuesto(Integer idPresupuesto) {
        return movimientoCrudRepository.findByIdPresupuestoAndActivoTrueOrderByIdPresupuestoAsc(idPresupuesto).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getAll() {
        return movimientoCrudRepository.findAllByActivoTrueOrderByTipoMovimientoNombreTipoMovimientoAsc().map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getAllDeleted() {
        return movimientoCrudRepository.findAllByActivoFalseOrderByTipoMovimientoNombreTipoMovimientoAsc().map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    @Transactional
    public MovimientoDto save(MovimientoDto movimientoDto) throws IllegalArgumentException {
        Movimiento movimiento=MovimientoMapper.INSTANCE.toEntity(movimientoDto);
        // Retrieve the associated Cuenta and Presupuesto
        Optional<Cuenta> cuentaOpt = cuentaCrudRepository.findByIdCuenta(movimiento.getIdCuenta());
        Optional<Presupuesto> presupuestoOpt = presupuestoCrudRepository.findByIdPresupuesto(movimiento.getIdPresupuesto());

        if (!cuentaOpt.isPresent() || !presupuestoOpt.isPresent()) {
            throw new IllegalArgumentException("Cuenta or Presupuesto not found");
        }

        Cuenta cuenta = cuentaOpt.get();
        Presupuesto presupuesto = presupuestoOpt.get();

        if (movimiento.getMonto() < 0) {
            if (cuenta.getFondos() + movimiento.getMonto() < 0) {
                throw new IllegalArgumentException("Insufficient funds in the account");
            } else {
                cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
                presupuesto.setMontoEjecutado(presupuesto.getMontoEjecutado() - movimiento.getMonto());
            }
        } else {
            cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
            presupuesto.setMontoEjecutado(presupuesto.getMontoEjecutado() + movimiento.getMonto());
        }

        // Save the Movimiento, Cuenta, and Presupuesto
        Movimiento savedMovimiento = movimientoCrudRepository.save(movimiento);
        cuentaCrudRepository.save(cuenta);
        presupuestoCrudRepository.save(presupuesto);

        return MovimientoMapper.INSTANCE.toDto(savedMovimiento);
    }

    @Override
    public boolean delete(Integer idMovimiento) {
        return  getByIdMovimiento(idMovimiento).map(presupuesto -> {
            movimientoCrudRepository.setActivoByIdMovimiento(idMovimiento,false);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean unDelete(Integer idMovimiento) {
        return  getByIdMovimiento(idMovimiento).map(presupuesto -> {
            movimientoCrudRepository.setActivoByIdMovimiento(idMovimiento,true);
            return true;
        }).orElse(false);
    }

}
