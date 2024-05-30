package com.dev.gestorgastos.persistence;
import com.dev.gestorgastos.domain.DenominacionDto;
import com.dev.gestorgastos.domain.ProveedorDto;
import com.dev.gestorgastos.persistence.entity.Denominacion;
import com.dev.gestorgastos.persistence.entity.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {
    ProveedorMapper INSTANCE = Mappers.getMapper(ProveedorMapper.class);

    ProveedorDto toDto(Proveedor proveedor);

    List<ProveedorDto> toDtos(List<Proveedor> proveedores);

    Proveedor toEntity(ProveedorDto proveedorDto);
}
