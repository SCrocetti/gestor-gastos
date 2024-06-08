package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.dto.TransaccionDto;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransaccionDtoRepository {
    public Optional<TransaccionDto> getByIdTransaccion(Integer idTransaccion);
    public Optional<List<TransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion);
    public Optional<List<TransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion);
    public Optional<List<TransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion);
    public Optional<List<TransaccionDto>> getActivosByIdCuentaEgreso(Integer idCuentaEgreso);
    public Optional<List<TransaccionDto>> getActivosByIdCuentaIngreso(Integer idCuentaIngreso);
    public Optional<List<TransaccionDto>> getActivosByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion);
    public Optional<List<TransaccionDto>> getActivosByFechaHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    public Optional<List<TransaccionDto>> getAll() ;
    public Optional<List<TransaccionDto>> getAllDeleted();
    public TransaccionDto save(TransaccionDto transaccionDto);
    public boolean delete(Integer idTransaccion);
    public boolean unDelete(Integer idTransaccion);
}
