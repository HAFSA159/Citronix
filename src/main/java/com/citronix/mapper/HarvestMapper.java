package com.citronix.mapper;

import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(target = "season", source = "season", qualifiedByName = "seasonToString")
    HarvestResponse toDTO(Harvest harvest);

    @Named("seasonToString")
    default String mapSeasonToString(Season season) {
        return season != null ? season.name() : null;
    }
}
