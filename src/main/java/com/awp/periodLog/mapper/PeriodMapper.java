package com.awp.periodLog.mapper;

import com.awp.periodLog.dto.PeriodRequestDTO;
import com.awp.periodLog.dto.PeriodResponseDTO;
import com.awp.periodLog.model.Period;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PeriodMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Period toEntity(PeriodRequestDTO periodRequestDTO);

    @Mapping(source = "user.id", target = "userId")
    PeriodResponseDTO toResponse(Period period);
}
