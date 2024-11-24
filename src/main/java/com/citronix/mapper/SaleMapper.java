package com.citronix.mapper;

import com.citronix.dto.response.SaleResponse;
import com.citronix.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SaleMapper {
    @Mapping(source="harvestId",target="harvest.id")
    Sale toEntity(SaleResponse saleDTO);

    @Mapping(source="harvest.id", target="harvestId")
    SaleResponse toDTO(Sale sale);

    @Mapping(source="harvestId",target="harvest.id")
    @Mapping(target = "id",ignore = true)
    void updateEntityFromDTO(SaleResponse saleDTO, @MappingTarget Sale sale);
}