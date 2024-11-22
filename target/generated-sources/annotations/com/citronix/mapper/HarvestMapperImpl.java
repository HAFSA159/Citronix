package com.citronix.mapper;

import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T12:15:15+0100",
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

        harvestResponse.setFieldId( harvestFieldId( harvest ) );
        harvestResponse.setId( harvest.getId() );
        harvestResponse.setHarvestDate( harvest.getHarvestDate() );
        if ( harvest.getSeason() != null ) {
            harvestResponse.setSeason( harvest.getSeason().name() );
        }
        harvestResponse.setTotalQuantity( harvest.getTotalQuantity() );

        return harvestResponse;
    }

    @Override
    public Harvest toEntity(HarvestResponse harvestDTO) {
        if ( harvestDTO == null ) {
            return null;
        }

        Harvest.HarvestBuilder harvest = Harvest.builder();

        harvest.field( harvestResponseToField( harvestDTO ) );
        harvest.id( harvestDTO.getId() );
        harvest.harvestDate( harvestDTO.getHarvestDate() );
        if ( harvestDTO.getSeason() != null ) {
            harvest.season( Enum.valueOf( Season.class, harvestDTO.getSeason() ) );
        }
        harvest.totalQuantity( harvestDTO.getTotalQuantity() );

        return harvest.build();
    }

    @Override
    public void updateEntityFromDTO(HarvestResponse harvestDTO, Harvest harvest) {
        if ( harvestDTO == null ) {
            return;
        }

        harvest.setHarvestDate( harvestDTO.getHarvestDate() );
        if ( harvestDTO.getSeason() != null ) {
            harvest.setSeason( Enum.valueOf( Season.class, harvestDTO.getSeason() ) );
        }
        else {
            harvest.setSeason( null );
        }
        harvest.setTotalQuantity( harvestDTO.getTotalQuantity() );
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

    protected Field harvestResponseToField(HarvestResponse harvestResponse) {
        if ( harvestResponse == null ) {
            return null;
        }

        Field.FieldBuilder field = Field.builder();

        field.id( harvestResponse.getFieldId() );

        return field.build();
    }
}
