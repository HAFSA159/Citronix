package com.citronix.mapper;

import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Harvest;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-24T12:41:04+0100",
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
        harvestResponse.setFieldId( harvestFieldId( harvest ) );
        harvestResponse.setId( harvest.getId() );
        harvestResponse.setHarvestDate( harvest.getHarvestDate() );
        harvestResponse.setTotalQuantity( harvest.getTotalQuantity() );

        return harvestResponse;
    }

    private Long harvestFieldId(Harvest harvest) {
        if ( harvest == null ) {
            return null;
        }
        Field field = harvest.getField();
        if ( field == null ) {
            return null;
        }
        Long id = field.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
