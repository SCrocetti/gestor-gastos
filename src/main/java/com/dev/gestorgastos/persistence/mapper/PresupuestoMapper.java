package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PresupuestoDto;
import com.dev.gestorgastos.persistence.entity.Presupuesto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PresupuestoMapper {
    PresupuestoMapper INSTANCE = Mappers.getMapper(PresupuestoMapper.class);

    PresupuestoDto toDto(Presupuesto presupuesto);

    List<PresupuestoDto> toDtos(List<Presupuesto> presupuestos);

    Presupuesto toEntity(PresupuestoDto presupuestoDto);
}