package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.MovimientoDto;
import com.dev.gestorgastos.domain.dto.TransaccionDto;
import com.dev.gestorgastos.persistence.entity.Movimiento;
import com.dev.gestorgastos.persistence.entity.Transaccion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransaccionMapper {
    TransaccionMapper INSTANCE = Mappers.getMapper(TransaccionMapper.class);

    TransaccionDto toDto(Transaccion transaccion);

    List<TransaccionDto> toDtos(List<Transaccion> transacciones);

    Transaccion toEntity(TransaccionDto transaccionDto);
}
