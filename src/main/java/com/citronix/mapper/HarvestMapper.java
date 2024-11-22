package com.citronix.mapper;


import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Harvest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface HarvestMapper {
    @Mapping(source = "field.id", target = "fieldId")  // Mapping field ID correctly
    HarvestResponse toDTO(Harvest harvest);

    @Mapping(target = "field.id", source = "fieldId")  // Reverse mapping to set the field object from fieldId
    Harvest toEntity(HarvestResponse harvestDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(HarvestResponse harvestDTO, @MappingTarget Harvest harvest);
}
