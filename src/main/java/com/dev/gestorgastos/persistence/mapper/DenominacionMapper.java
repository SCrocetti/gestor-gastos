package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.DenominacionDto;
import com.dev.gestorgastos.persistence.entity.Denominacion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DenominacionMapper {
    DenominacionMapper INSTANCE = Mappers.getMapper(DenominacionMapper.class);

    DenominacionDto toDto(Denominacion denominacion);

    List<DenominacionDto> toDtos(List<Denominacion> denominaciones);

    Denominacion toEntity(DenominacionDto denominacionDto);
}
