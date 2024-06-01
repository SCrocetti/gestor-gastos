package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.domain.TipoMovimientoDto;
import com.dev.gestorgastos.persistence.entity.Proveedor;
import com.dev.gestorgastos.persistence.entity.TipoMovimiento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoMovimientoMapper {
    TipoMovimientoMapper INSTANCE = Mappers.getMapper(TipoMovimientoMapper.class);

    TipoMovimientoDto toDto(TipoMovimiento tipoMovimiento);

    List<TipoMovimientoDto> toDtos(List<TipoMovimiento> tiposMovimientos);

    TipoMovimiento toEntity(TipoMovimientoDto tipoMovimientoDto);
}
