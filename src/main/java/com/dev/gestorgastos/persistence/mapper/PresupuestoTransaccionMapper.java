package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.domain.dto.PresupuestoTransaccionDto;
import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import com.dev.gestorgastos.persistence.entity.PresupuestoTransaccion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PresupuestoTransaccionMapper {
    PresupuestoTransaccionMapper INSTANCE = Mappers.getMapper(PresupuestoTransaccionMapper.class);

    PresupuestoTransaccionDto toDto(PresupuestoTransaccion presupuestoTransaccion);

    List<PresupuestoTransaccionDto> toDtos(List<PresupuestoTransaccion> presupuestosTransacciones);

    PresupuestoTransaccion toEntity(PresupuestoTransaccionDto presupuestoTransaccionDto);
}
