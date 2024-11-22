package com.citronix.mapper;

import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T11:56:19+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class FarmMapperImpl implements FarmMapper {

    @Override
    public FarmResponse toDTO(Farm farm) {
        if ( farm == null ) {
            return null;
        }

        FarmResponse farmResponse = new FarmResponse();

        farmResponse.setId( farm.getId() );
        farmResponse.setName( farm.getName() );
        farmResponse.setArea( farm.getArea() );
        farmResponse.setLocation( farm.getLocation() );
        farmResponse.setCreationDate( farm.getCreationDate() );

        return farmResponse;
    }

    @Override
    public Farm toEntity(FarmResponse farmDTO) {
        if ( farmDTO == null ) {
            return null;
        }

        Farm.FarmBuilder farm = Farm.builder();

        farm.id( farmDTO.getId() );
        farm.name( farmDTO.getName() );
        farm.area( farmDTO.getArea() );
        farm.location( farmDTO.getLocation() );
        farm.creationDate( farmDTO.getCreationDate() );

        return farm.build();
    }

    @Override
    public void updateEntityFromDTO(FarmResponse farmDTO, Farm farm) {
        if ( farmDTO == null ) {
            return;
        }

        farm.setName( farmDTO.getName() );
        farm.setArea( farmDTO.getArea() );
        farm.setLocation( farmDTO.getLocation() );
        farm.setCreationDate( farmDTO.getCreationDate() );
    }

    @Override
    public List<FarmResponse> toDtoList(List<Farm> farms) {
        if ( farms == null ) {
            return null;
        }

        List<FarmResponse> list = new ArrayList<FarmResponse>( farms.size() );
        for ( Farm farm : farms ) {
            list.add( toDTO( farm ) );
        }

        return list;
    }

    @Override
    public List<Farm> toEntityList(List<FarmResponse> farmDTOs) {
        if ( farmDTOs == null ) {
            return null;
        }

        List<Farm> list = new ArrayList<Farm>( farmDTOs.size() );
        for ( FarmResponse farmResponse : farmDTOs ) {
            list.add( toEntity( farmResponse ) );
        }

        return list;
    }
}
