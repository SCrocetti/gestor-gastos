package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.ProveedorDto;
import com.dev.gestorgastos.persistence.entity.Proveedor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProveedorMapper {
    ProveedorMapper INSTANCE = Mappers.getMapper(ProveedorMapper.class);

    ProveedorDto toDto(Proveedor proveedor);

    List<ProveedorDto> toDtos(List<Proveedor> proveedores);

    Proveedor toEntity(ProveedorDto proveedorDto);
}
