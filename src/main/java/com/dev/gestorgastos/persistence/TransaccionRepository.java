package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.TransaccionDto;
import com.dev.gestorgastos.domain.repository.TransaccionDtoRepository;
import com.dev.gestorgastos.persistence.crud.*;
import com.dev.gestorgastos.persistence.entity.*;
import com.dev.gestorgastos.persistence.mapper.MovimientoMapper;
import com.dev.gestorgastos.persistence.mapper.TransaccionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TransaccionRepository implements TransaccionDtoRepository {
    @Autowired
    TransaccionCrudRepository transaccionCrudRepository;

    @Autowired
    TipoTransaccionCrudRepository tipoTransaccionCrudRepository;

    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    PresupuestoTransaccionCrudRepository presupuestoTransaccionCrudRepository;

    @Override
    public Optional<TransaccionDto> getByIdTransaccion(Integer idTransaccion) {
        return transaccionCrudRepository.findByIdTransaccion(idTransaccion).map(TransaccionMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return transaccionCrudRepository.findByTipoTransaccionNombreTipoTransaccionContainingAndActivoTrueOrderByIdTipoTransaccionAsc(nombreTipoTransaccion).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion) {
        return transaccionCrudRepository.findByTipoTransaccionNombreTipoTransaccionContainingAndActivoFalseOrderByIdTipoTransaccionAsc(nombreTipoTransaccion).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion) {
        return transaccionCrudRepository.findByIdTipoTransaccionAndActivoTrue(idTipoTransaccion).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByIdCuentaEgreso(Integer idCuentaEgreso) {
        return transaccionCrudRepository.findByIdCuentaEgresoAndActivoTrueOrderByIdTipoTransaccionAsc(idCuentaEgreso).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByIdCuentaIngreso(Integer idCuentaIngreso) {
        return transaccionCrudRepository.findByIdCuentaIngresoAndActivoTrueOrderByIdTipoTransaccionAsc(idCuentaIngreso).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion) {
        return transaccionCrudRepository.findByIdPresupuestoTransaccionAndActivoTrueOrderByIdTipoMovimientoAsc(idPresupuestoTransaccion).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return transaccionCrudRepository.findByFechaHoraBetweenAndActivoTrueOrderByFechaHoraAsc(startDateTime,endDateTime).map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getAll() {
        return transaccionCrudRepository.findAllByActivoTrueOrderByIdTipoTransaccionAsc().map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<TransaccionDto>> getAllDeleted() {
        return transaccionCrudRepository.findAllByActivoFalseOrderByIdTipoTransaccionAsc().map(TransaccionMapper.INSTANCE::toDtos);
    }

    @Override
    @Transactional
    public TransaccionDto save(TransaccionDto transaccionDto) {
        Transaccion transaccion=TransaccionMapper.INSTANCE.toEntity(transaccionDto);

        Optional<TipoTransaccion> tipoTransaccionOpt=tipoTransaccionCrudRepository.findByIdTipoTransaccion(transaccionDto.getIdTipoTransaccion());
        Optional<Cuenta> cuentaEgresoOpt = cuentaCrudRepository.findByIdCuenta(transaccionDto.getIdCuentaEgreso());
        Optional<Cuenta> cuentaIngresoOpt = cuentaCrudRepository.findByIdCuenta(transaccionDto.getIdCuentaIngreso());
        Optional<PresupuestoTransaccion> presupuestoTransaccionOpt = presupuestoTransaccionCrudRepository.findByIdPresupuestoTransaccion(transaccionDto.getIdPresupuestoTransaccion());

        if (!tipoTransaccionOpt.isPresent() ) {
            throw new IllegalArgumentException("TipoTransaccion  not found");
        }

        if (!cuentaEgresoOpt.isPresent() ) {
            throw new IllegalArgumentException("CuentaEgreso  not found");
        }
        if (!cuentaIngresoOpt.isPresent() ) {
            throw new IllegalArgumentException("CuentaIngreso  not found");
        }

        if (!presupuestoTransaccionOpt.isPresent()) {
            throw new IllegalArgumentException("PresupuestoTransaccion not found");
        }
        Cuenta cuentaEgreso = cuentaEgresoOpt.get();
        Cuenta cuentaIngreso = cuentaIngresoOpt.get();
        PresupuestoTransaccion presupuestoTransaccion=presupuestoTransaccionOpt.get();

        if (transaccion.getMontoEgreso() < 0) {
            throw new IllegalArgumentException("MontoEgreso cannot be less than 0");
        } else {
            if (cuentaEgreso.getFondos() - transaccion.getMontoEgreso() < 0) {
                throw new IllegalArgumentException("Insuficent funds in the cuentaEgreso");
            }
            cuentaEgreso.setFondos(cuentaEgreso.getFondos() - transaccion.getMontoEgreso());
            presupuestoTransaccion.setMontoDebitado(presupuestoTransaccion.getMontoDebitado()+transaccion.getMontoEgreso());
        }
        if (transaccion.getMontoIngreso() < 0) {
            throw new IllegalArgumentException("MontoIngreso cannot be less than 0");
        } else {
            cuentaIngreso.setFondos(cuentaIngreso.getFondos()+transaccion.getMontoIngreso());
            presupuestoTransaccion.setMontoAcreditado(presupuestoTransaccion.getMontoAcreditado()+transaccion.getMontoIngreso());
        }

        Transaccion savedTransaccion = transaccionCrudRepository.save(transaccion);
        cuentaCrudRepository.save(cuentaEgreso);
        cuentaCrudRepository.save(cuentaIngreso);
        presupuestoTransaccionCrudRepository.save(presupuestoTransaccion);

        return TransaccionMapper.INSTANCE.toDto(savedTransaccion);
    }

    @Override
    @Transactional
    public boolean delete(Integer idTransaccion) {
        Optional<Transaccion> transaccionOpt= transaccionCrudRepository.findByIdTransaccion(idTransaccion);
        if(transaccionOpt.isPresent()){
            Transaccion transaccion=transaccionOpt.get();
            Cuenta cuentaEgreso=transaccion.getCuentaEgreso();
            Cuenta cuentaIngreso=transaccion.getCuentaIngreso();
            PresupuestoTransaccion presupuestoTransaccion=transaccion.getPresupuestoTransaccion();
            if(presupuestoTransaccion.getMontoDebitado()-transaccion.getMontoEgreso()<0){
               throw new IllegalArgumentException("MontoDebitado on the PresupuestoTransaccion cannot be less than 0");
            }
            if(presupuestoTransaccion.getMontoAcreditado()-transaccion.getMontoIngreso()<0){
                throw new IllegalArgumentException("MontoAcreditado on the PresupuestoTransaccion cannot be less than 0");
            }
            if(cuentaIngreso.getFondos()-transaccion.getMontoIngreso()<0){
                throw new IllegalArgumentException("Insuficent funds in the cuentaIngreso");
            }
            presupuestoTransaccion.setMontoDebitado(presupuestoTransaccion.getMontoDebitado()-transaccion.getMontoEgreso());
            presupuestoTransaccion.setMontoAcreditado(presupuestoTransaccion.getMontoAcreditado()-transaccion.getMontoIngreso());
            cuentaIngreso.setFondos(cuentaIngreso.getFondos()-transaccion.getMontoIngreso());
            cuentaEgreso.setFondos(cuentaEgreso.getFondos()+transaccion.getMontoEgreso());

            cuentaCrudRepository.save(cuentaIngreso);
            cuentaCrudRepository.save(cuentaEgreso);
            presupuestoTransaccionCrudRepository.save(presupuestoTransaccion);
            transaccionCrudRepository.setActivoByIdTransaccion(idTransaccion,false);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean unDelete(Integer idTransaccion) {
        Optional<Transaccion> transaccionOpt= transaccionCrudRepository.findByIdTransaccion(idTransaccion);
        if(transaccionOpt.isPresent()){
            Transaccion transaccion=transaccionOpt.get();
            Cuenta cuentaEgreso=transaccion.getCuentaEgreso();
            Cuenta cuentaIngreso=transaccion.getCuentaIngreso();
            PresupuestoTransaccion presupuestoTransaccion=transaccion.getPresupuestoTransaccion();
            if (cuentaEgreso.getFondos() - transaccion.getMontoEgreso() < 0) {
                throw new IllegalArgumentException("Insuficent funds in the cuentaEgreso");
            }
            cuentaEgreso.setFondos(cuentaEgreso.getFondos() - transaccion.getMontoEgreso());
            cuentaIngreso.setFondos(cuentaIngreso.getFondos()+transaccion.getMontoIngreso());
            presupuestoTransaccion.setMontoDebitado(presupuestoTransaccion.getMontoDebitado()+transaccion.getMontoEgreso());
            presupuestoTransaccion.setMontoAcreditado(presupuestoTransaccion.getMontoAcreditado()+transaccion.getMontoIngreso());
            cuentaCrudRepository.save(cuentaIngreso);
            cuentaCrudRepository.save(cuentaEgreso);
            presupuestoTransaccionCrudRepository.save(presupuestoTransaccion);
            transaccionCrudRepository.setActivoByIdTransaccion(idTransaccion,true);
            return true;
        }else{
            return false;
        }
    }
}
