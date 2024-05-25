package com.dev.gestorgastos.persistence;
import com.dev.gestorgastos.domain.PaisDto;
import com.dev.gestorgastos.persistence.entity.Pais;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaisMapper {
    PaisMapper INSTANCE = Mappers.getMapper(PaisMapper.class);

    PaisDto toDto(Pais pais);

    List<PaisDto> toDtos(List<Pais> paises);

    Pais toEntity(PaisDto paisDto);
}
