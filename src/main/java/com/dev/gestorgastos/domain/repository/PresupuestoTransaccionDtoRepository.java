package com.dev.gestorgastos.domain.repository;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.domain.dto.PresupuestoTransaccionDto;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.PresupuestoTransaccion;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PresupuestoTransaccionDtoRepository {
    public Optional<PresupuestoTransaccionDto> getByIdPresupuestoTransaccion(Integer idPresupuestoTransaccion);
    public Optional<List<PresupuestoTransaccionDto>> getActivosByNombreTipoTransaccionContains(String nombreTipoTransaccion);
    public Optional<List<PresupuestoTransaccionDto>> getInactivosByNombreTipoTransaccionContains(String nombreTipoTransaccion);
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdTipoTransaccion(Integer idTipoTransaccion);
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdCuentaEgreso(Integer idCuentaEgreso);
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdCuentaIngreso(Integer idCuentaIngreso);
    public Optional<List<PresupuestoTransaccionDto>> getActivosByIdPlan(Integer idPlan);
    public Optional<List<PresupuestoTransaccionDto>> getAll() ;
    public Optional<List<PresupuestoTransaccionDto>> getAllDeleted();
    public PresupuestoTransaccionDto save(PresupuestoTransaccionDto presupuestoTransaccionDto);
    public boolean delete(Integer idPresupuestoTransaccion);
    public boolean unDelete(Integer idPresupuestoTransaccion);
}
