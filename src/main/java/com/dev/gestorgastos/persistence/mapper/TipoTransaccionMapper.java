package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.TipoMovimientoDto;
import com.dev.gestorgastos.domain.dto.TipoTransaccionDto;
import com.dev.gestorgastos.persistence.entity.TipoMovimiento;
import com.dev.gestorgastos.persistence.entity.TipoTransaccion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TipoTransaccionMapper {
    TipoTransaccionMapper INSTANCE = Mappers.getMapper(TipoTransaccionMapper.class);

    TipoTransaccionDto toDto(TipoTransaccion tipoTransaccion);

    List<TipoTransaccionDto> toDtos(List<TipoTransaccion> tiposTransaccion);

    TipoTransaccion toEntity(TipoTransaccionDto tipoTransaccionDto);
}
