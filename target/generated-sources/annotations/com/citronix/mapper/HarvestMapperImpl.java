package com.citronix.mapper;

import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Harvest;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-24T12:03:40+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class HarvestMapperImpl implements HarvestMapper {

    @Override
    public HarvestResponse toDTO(Harvest harvest) {
        if ( harvest == null ) {
            return null;
        }

        HarvestResponse harvestResponse = new HarvestResponse();

        harvestResponse.setSeason( mapSeasonToString( harvest.getSeason() ) );
        harvestResponse.setId( harvest.getId() );
        harvestResponse.setHarvestDate( harvest.getHarvestDate() );
        harvestResponse.setTotalQuantity( harvest.getTotalQuantity() );

        return harvestResponse;
    }
}
