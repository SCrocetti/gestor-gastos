package com.dev.gestorgastos.persistence;
import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.entity.Denominacion;
import com.dev.gestorgastos.persistence.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DenominacionMapper {
    DenominacionMapper INSTANCE = Mappers.getMapper(DenominacionMapper.class);

    DenominacionDto toDto(Denominacion denominacion);

    List<DenominacionDto> toDtos(List<Denominacion> denominaciones);

    Denominacion toEntity(DenominacionDto denominacionDto);
}
