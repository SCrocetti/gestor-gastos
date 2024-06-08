package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.repository.MovimientoDtoRepository;
import com.dev.gestorgastos.persistence.crud.CuentaCrudRepository;
import com.dev.gestorgastos.persistence.crud.MovimientoCrudRepository;
import com.dev.gestorgastos.persistence.crud.PresupuestoMovimientoCrudRepository;
import com.dev.gestorgastos.persistence.crud.TipoMovimientoCrudRepository;
import com.dev.gestorgastos.persistence.entity.Cuenta;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import com.dev.gestorgastos.persistence.entity.TipoMovimiento;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.MovimientoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MovimientoRepository implements MovimientoDtoRepository {
    @Autowired
    MovimientoCrudRepository movimientoCrudRepository;

    @Autowired
    TipoMovimientoCrudRepository tipoMovimientoCrudRepository;

    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    PresupuestoMovimientoCrudRepository presupuestoMovimientoCrudRepository;

    @Override
    public Optional<MovimientoDto> getByIdMovimiento(Integer idMovimiento) {
        return movimientoCrudRepository.findByIdMovimiento(idMovimiento).map(MovimientoMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoTrueOrderByIdTipoMovimientoAsc(nombreTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getInactivosByNombreTipoMovimientoContains(String nombreTipoMovimiento) {
        return movimientoCrudRepository.findByTipoMovimientoNombreTipoMovimientoContainingAndActivoFalseOrderByIdTipoMovimientoAsc(nombreTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdTipoMovimiento(Integer idTipoMovimiento) {
        return movimientoCrudRepository.findByIdTipoMovimientoAndActivoTrue(idTipoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdCuenta(Integer idCuenta) {
        return movimientoCrudRepository.findByIdCuentaAndActivoTrueOrderByIdTipoMovimientoAsc(idCuenta).map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getActivosByIdPresupuestoMovimiento(Integer idPresupuestoMovimiento) {
        return movimientoCrudRepository.findByIdPresupuestoMovimientoAndActivoTrueOrderByIdTipoMovimientoAsc(idPresupuestoMovimiento).map(MovimientoMapper.INSTANCE::toDtos);
    }
    @Override
    public Optional<List<MovimientoDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return movimientoCrudRepository.findByFechaHoraBetweenAndActivoTrueOrderByFechaHoraAsc(startDateTime,endDateTime).map(MovimientoMapper.INSTANCE::toDtos);
    }


    @Override
    public Optional<List<MovimientoDto>> getAll() {
        return movimientoCrudRepository.findAllByActivoTrueOrderByIdTipoMovimientoAsc().map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<MovimientoDto>> getAllDeleted() {
        return movimientoCrudRepository.findAllByActivoFalseOrderByIdTipoMovimientoAsc().map(MovimientoMapper.INSTANCE::toDtos);
    }

    @Override
    @Transactional
    public MovimientoDto save(MovimientoDto movimientoDto) throws IllegalArgumentException {
        Movimiento movimiento=MovimientoMapper.INSTANCE.toEntity(movimientoDto);

        Optional<TipoMovimiento> tipoMovimientoOpt=tipoMovimientoCrudRepository.findByIdTipoMovimiento(movimientoDto.getIdTipoMovimiento());
        Optional<Cuenta> cuentaOpt = cuentaCrudRepository.findByIdCuenta(movimientoDto.getIdCuenta());
        Optional<PresupuestoMovimiento> presupuestoMovimientoOpt = presupuestoMovimientoCrudRepository.findByIdPresupuestoMovimiento(movimientoDto.getIdPresupuestoMovimiento());

        if (!tipoMovimientoOpt.isPresent() ) {
            throw new IllegalArgumentException("TipoMovimiento  not found");
        }

        if (!cuentaOpt.isPresent() ) {
            throw new IllegalArgumentException("Cuenta  not found");
        }

        if (!presupuestoMovimientoOpt.isPresent()) {
            throw new IllegalArgumentException("PresupuestoMovimiento not found");
        }
        Cuenta cuenta = cuentaOpt.get();
        PresupuestoMovimiento presupuestoMovimiento = presupuestoMovimientoOpt.get();

        if (movimiento.getMonto() < 0) {
            if (cuenta.getFondos() + movimiento.getMonto() < 0) {
                throw new IllegalArgumentException("Insufficient funds in the account");
            } else {
                cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
                presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() - movimiento.getMonto());
            }
        } else {
            cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
            presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() + movimiento.getMonto());
        }

        Movimiento savedMovimiento = movimientoCrudRepository.save(movimiento);
        cuentaCrudRepository.save(cuenta);
        presupuestoMovimientoCrudRepository.save(presupuestoMovimiento);

        return MovimientoMapper.INSTANCE.toDto(savedMovimiento);
    }

    @Override
    @Transactional
    public boolean delete(Integer idMovimiento) {
        Optional<Movimiento> movimientoOpt= movimientoCrudRepository.findByIdMovimiento(idMovimiento);
        if(movimientoOpt.isPresent()){
            Movimiento movimiento=movimientoOpt.get();
            Cuenta cuenta=movimiento.getCuenta();
            PresupuestoMovimiento presupuestoMovimiento=movimiento.getPresupuestoMovimiento();
            if (movimiento.getMonto() > 0) {
                if (cuenta.getFondos() - movimiento.getMonto() < 0) {
                    throw new EntityCannotBeDeletedException("Insufficient funds in the account");
                }
                if(presupuestoMovimiento.getMontoEjecutado() - movimiento.getMonto()<0){
                    throw new EntityCannotBeDeletedException("MontoEjecutado on the PresupuestoMovimiento cannot be less than 0");
                }
                cuenta.setFondos(cuenta.getFondos() - movimiento.getMonto());
                presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() - movimiento.getMonto());

            } else {
                cuenta.setFondos(cuenta.getFondos() - movimiento.getMonto());
                presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() - movimiento.getMonto());
            }
            cuentaCrudRepository.save(cuenta);
            presupuestoMovimientoCrudRepository.save(presupuestoMovimiento);
            movimientoCrudRepository.setActivoByIdMovimiento(idMovimiento,false);
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional
    public boolean unDelete(Integer idMovimiento) {
        Optional<Movimiento> movimientoOpt= movimientoCrudRepository.findByIdMovimiento(idMovimiento);
        if(movimientoOpt.isPresent()){
            Movimiento movimiento=movimientoOpt.get();
            Cuenta cuenta=movimiento.getCuenta();
            PresupuestoMovimiento presupuestoMovimiento=movimiento.getPresupuestoMovimiento();
            if (movimiento.getMonto() < 0) {
                if (cuenta.getFondos() + movimiento.getMonto() < 0) {
                    throw new EntityCannotBeUndeletedException("Insufficient funds in the account");
                } else {
                    cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
                    presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() - movimiento.getMonto());
                }
            } else {
                cuenta.setFondos(cuenta.getFondos() + movimiento.getMonto());
                presupuestoMovimiento.setMontoEjecutado(presupuestoMovimiento.getMontoEjecutado() + movimiento.getMonto());
            }
            cuentaCrudRepository.save(cuenta);
            presupuestoMovimientoCrudRepository.save(presupuestoMovimiento);
            movimientoCrudRepository.setActivoByIdMovimiento(idMovimiento,true);
            return true;
        }else{
            return false;
        }
    }

}
