package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.CuentaDto;
import com.dev.gestorgastos.persistence.entity.Cuenta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CuentaMapper {
    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    CuentaDto toDto(Cuenta cuenta);

    List<CuentaDto> toDtos(List<Cuenta> cuentas);

    Cuenta toEntity(CuentaDto cuentaDto);
}
