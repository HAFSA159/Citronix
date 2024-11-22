package com.citronix.mapper;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.entity.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
@Component
public interface FieldMapper {

    @Mapping(source = "farm.id", target = "farmId")  // Ensure the farm.id is mapped to farmId in the response
    FieldResponse toDTO(Field field);

    @Mapping(source = "farmId", target = "farm.id")  // Map farmId to farm.id when converting to entity
    Field toEntity(FieldRequest fieldRequest);

    @Mapping(target = "id", ignore = true)  // Ignore the ID during updates
    void updateEntityFromDTO(FieldResponse fieldResponse, @MappingTarget Field field);
}
