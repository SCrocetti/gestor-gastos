package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.PersonaDto;
import com.dev.gestorgastos.persistence.entity.Persona;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

    PersonaDto toDto(Persona persona);

    List<PersonaDto> toDtos(List<Persona> personas);

    Persona toEntity(PersonaDto personaDto);
}
