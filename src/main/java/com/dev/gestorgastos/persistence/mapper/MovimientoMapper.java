package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovimientoMapper {
    MovimientoMapper INSTANCE = Mappers.getMapper(MovimientoMapper.class);

    MovimientoDto toDto(Movimiento movimiento);

    List<MovimientoDto> toDtos(List<Movimiento> movimientos);

    Movimiento toEntity(MovimientoDto movimientoDto);
}
