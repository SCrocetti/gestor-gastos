package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PersonaDto;
import com.dev.gestorgastos.persistence.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    PersonaDto toDto(Persona persona);

    List<PersonaDto> toDtos(List<Persona> personas);

    Persona toEntity(PersonaDto personaDto);
}
