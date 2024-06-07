package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.persistence.entity.PresupuestoMovimiento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PresupuestoMovimientoMapper {
    PresupuestoMovimiento INSTANCE = Mappers.getMapper(PresupuestoMovimiento.class);

    PresupuestoMovimientoDto toDto(PresupuestoMovimiento presupuestoMovimiento);

    List<PresupuestoMovimientoDto> toDtos(List<PresupuestoMovimiento> presupuestosMovimientos);

    PresupuestoMovimiento toEntity(PresupuestoMovimientoDto presupuestoMovimientoDto);
}
