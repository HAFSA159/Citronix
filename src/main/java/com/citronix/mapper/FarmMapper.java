package com.citronix.mapper;

import com.citronix.dto.request.FarmRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
@Component
public interface FarmMapper {
    FarmResponse toDTO(Farm farm);

    Farm toEntity(FarmResponse farmDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(FarmResponse farmDTO, @MappingTarget Farm farm);
    List<FarmResponse> toDtoList(List<Farm> farms);
    List<Farm> toEntityList(List<FarmResponse> farmDTOs);
}