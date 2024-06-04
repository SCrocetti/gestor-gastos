package com.dev.gestorgastos.persistence.mapper;
import com.dev.gestorgastos.domain.dto.PlanDto;
import com.dev.gestorgastos.persistence.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlanMapper {
    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    PlanDto toDto(Plan plan);

    List<PlanDto> toDtos(List<Plan> planes);

    Plan toEntity(PlanDto planDto);
}
