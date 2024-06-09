package com.dev.gestorgastos.persistence;

import com.dev.gestorgastos.domain.dto.CuentaDto;
import com.dev.gestorgastos.domain.repository.CuentaDtoRepository;
import com.dev.gestorgastos.persistence.crud.CuentaCrudRepository;
import com.dev.gestorgastos.persistence.crud.DenominacionCrudRepository;
import com.dev.gestorgastos.persistence.crud.PersonaCrudRepository;
import com.dev.gestorgastos.persistence.crud.ProveedorCrudRepository;
import com.dev.gestorgastos.persistence.entity.*;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeDeletedException;
import com.dev.gestorgastos.persistence.exception.EntityCannotBeUndeletedException;
import com.dev.gestorgastos.persistence.mapper.CuentaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CuentaRepository implements CuentaDtoRepository {
    @Autowired
    CuentaCrudRepository cuentaCrudRepository;

    @Autowired
    ProveedorCrudRepository proveedorCrudRepository;

    @Autowired
    DenominacionCrudRepository denominacionCrudRepository;

    @Autowired
    PersonaCrudRepository personaCrudRepository;

    @Override
    public Optional<CuentaDto> getByIdCuenta(Integer idCuenta) {
        return cuentaCrudRepository.findByIdCuenta(idCuenta).map(CuentaMapper.INSTANCE::toDto);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByDescripcionContains(String descripcion) {
        return cuentaCrudRepository.findByDescripcionContainingAndActivoTrueOrderByDescripcionAsc(descripcion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getInactivosByDescripcionContains(String descripcion) {
        return cuentaCrudRepository.findByDescripcionContainingAndActivoFalseOrderByDescripcionAsc(descripcion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdProveedor(Integer idProveedor) {
        return cuentaCrudRepository.findByIdProveedorAndActivoTrueOrderByProveedorNombreProveedorAscDescripcionAsc(idProveedor).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdDenominacion(Integer idDenominacion) {
        return cuentaCrudRepository.findByIdDenominacionAndActivoTrueOrderByDenominacionNombreDenominacionAscDescripcionAsc(idDenominacion).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getActivosByIdPersona(Integer idPersona) {
        return cuentaCrudRepository.findByIdPersonaAndActivoTrueOrderByPersonaNombresAscPersonaApellidosAscDescripcionAsc(idPersona).map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getAll() {
        return cuentaCrudRepository.findAllByActivoTrueOrderByDescripcionAsc().map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    public Optional<List<CuentaDto>> getAllDeleted() {
        return cuentaCrudRepository.findAllByActivoFalseOrderByDescripcionAsc().map(CuentaMapper.INSTANCE::toDtos);
    }

    @Override
    @Transactional
    public CuentaDto save(CuentaDto cuentaDto) throws IllegalArgumentException {
        Cuenta cuenta= CuentaMapper.INSTANCE.toEntity(cuentaDto);

        Optional<Proveedor> proveedorOpt = proveedorCrudRepository.findByIdProveedor(cuentaDto.getIdProveedor());
        Optional<Denominacion> denominacionOpt = denominacionCrudRepository.findByIdDenominacion(cuentaDto.getIdDenominacion());
        Optional<Persona> personaOpt = personaCrudRepository.findByIdPersona(cuentaDto.getIdPersona());

        if (!proveedorOpt.isPresent() ) {
            throw new IllegalArgumentException("Proveedor  not found");
        }

        if (!denominacionOpt.isPresent()) {
            throw new IllegalArgumentException("Denominacion not found");
        }
        if (!personaOpt.isPresent()) {
            throw new IllegalArgumentException("Persona not found");
        }
        return CuentaMapper.INSTANCE.toDto(cuentaCrudRepository.save(cuenta));
    }
    @Override
    @Transactional
    public boolean delete(Integer idCuenta) {
        return cuentaCrudRepository.findByIdCuenta(idCuenta).map(cuenta -> {
            if(!cuenta.isActivo()){
                throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has already been deleted.");
            }
            if (cuenta.getPresupuestosMovimientos() != null && !cuenta.getPresupuestosMovimientos().isEmpty()) {
                for(PresupuestoMovimiento presupuestoMovimiento : cuenta.getPresupuestosMovimientos()){
                    if(presupuestoMovimiento.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active PresupuestosMovimientos.");
                    }
                }
            }
            if (cuenta.getPresupuestosTransaccionesEgresos() != null && !cuenta.getPresupuestosTransaccionesEgresos().isEmpty()) {
                for(PresupuestoTransaccion presupuestoTransaccion: cuenta.getPresupuestosTransaccionesEgresos()){
                    if(presupuestoTransaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active PresupuestosTransaccionesEgresos.");
                    }
                }
            }
            if (cuenta.getPresupuestosTransaccionesIngresos() != null && !cuenta.getPresupuestosTransaccionesIngresos().isEmpty()) {
                for(PresupuestoTransaccion presupuestoTransaccion: cuenta.getPresupuestosTransaccionesIngresos()){
                    if(presupuestoTransaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active PresupuestosTransaccionesIngresos.");
                    }
                }
            }
            if (cuenta.getMovimientos() != null && !cuenta.getMovimientos().isEmpty()) {
                for(Movimiento movimiento: cuenta.getMovimientos()){
                    if(movimiento.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active Movimientos.");
                    }
                }
            }
            if (cuenta.getTransaccionesEgresos() != null && !cuenta.getTransaccionesEgresos().isEmpty()) {
                for(Transaccion transaccion:cuenta.getTransaccionesEgresos()){
                    if(transaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active TransaccionesEgresos.");
                    }
                }
            }
            if (cuenta.getTransaccionesIngresos() != null && !cuenta.getTransaccionesIngresos().isEmpty()) {
                for(Transaccion transaccion: cuenta.getTransaccionesIngresos()){
                    if(transaccion.isActivo()){
                        throw new EntityCannotBeDeletedException("Cannot delete Cuenta with id " + idCuenta + " as it has associated active TransaccionesIngresos.");
                    }
                }
            }
            cuentaCrudRepository.setActivoByIdCuenta(idCuenta, false);
            return true;
        }).orElse(false);
    }
    @Override
    public boolean unDelete(Integer idCuenta) {
        return  getByIdCuenta(idCuenta).map(cuenta -> {
            if(cuenta.isActivo()){
                throw new EntityCannotBeUndeletedException("Cannot undelete Cuenta with id " + idCuenta + " as it's not deleted.");
            }
            cuentaCrudRepository.setActivoByIdCuenta(idCuenta,true);
            return true;
        }).orElse(false);
    }
}
