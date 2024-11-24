package com.citronix.mapper;

import com.citronix.dto.response.HarvestDetailResponse;
import com.citronix.entity.HarvestDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface HarvestDetailMapper {

    @Mapping(source="harvestId", target = "harvest.id")
    @Mapping(source="treeId",target = "tree.id")
    HarvestDetail toEntity(HarvestDetailResponse harvestDetailDTO);

    @Mapping(source="harvest.id",target = "harvestId")
    @Mapping(source="tree.id",target = "treeId")
    HarvestDetailResponse toDTO(HarvestDetail harvestDetail);

    @Mapping(source="harvestId", target = "harvest.id")
    @Mapping(source="treeId",target = "tree.id")
    @Mapping(target = "id",ignore = true)
    void updateEntityFromDTO(HarvestDetailResponse harvestDetailDTO, @MappingTarget HarvestDetail harvestDetail);
}