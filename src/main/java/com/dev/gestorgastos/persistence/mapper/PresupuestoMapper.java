package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PresupuestoMovimientoDto;
import com.dev.gestorgastos.persistence.entity.Presupuesto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PresupuestoMapper {
    PresupuestoMapper INSTANCE = Mappers.getMapper(PresupuestoMapper.class);

    PresupuestoMovimientoDto toDto(Presupuesto presupuesto);

    List<PresupuestoMovimientoDto> toDtos(List<Presupuesto> presupuestos);

    Presupuesto toEntity(PresupuestoMovimientoDto presupuestoMovimientoDto);
}
