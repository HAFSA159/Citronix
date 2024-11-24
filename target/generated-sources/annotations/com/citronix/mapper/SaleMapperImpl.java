package com.citronix.mapper;

import com.citronix.dto.response.SaleResponse;
import com.citronix.entity.Harvest;
import com.citronix.entity.Sale;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-26T13:32:34+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 1.8.0_422-422 (OpenLogic-OpenJDK)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public Sale toEntity(SaleResponse saleDTO) {
        if ( saleDTO == null ) {
            return null;
        }

        Sale.SaleBuilder sale = Sale.builder();

        sale.harvest( saleResponseToHarvest( saleDTO ) );
        sale.id( saleDTO.getId() );
        sale.saleDate( saleDTO.getSaleDate() );
        sale.unitPrice( saleDTO.getUnitPrice() );
        sale.quantity( saleDTO.getQuantity() );
        sale.clientName( saleDTO.getClientName() );
        sale.totalRevenue( saleDTO.getTotalRevenue() );

        return sale.build();
    }

    @Override
    public SaleResponse toDTO(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleResponse.SaleResponseBuilder saleResponse = SaleResponse.builder();

        saleResponse.harvestId( saleHarvestId( sale ) );
        saleResponse.id( sale.getId() );
        saleResponse.saleDate( sale.getSaleDate() );
        saleResponse.unitPrice( sale.getUnitPrice() );
        saleResponse.quantity( sale.getQuantity() );
        saleResponse.clientName( sale.getClientName() );
        saleResponse.totalRevenue( sale.getTotalRevenue() );

        return saleResponse.build();
    }

    @Override
    public void updateEntityFromDTO(SaleResponse saleDTO, Sale sale) {
        if ( saleDTO == null ) {
            return;
        }

        if ( sale.getHarvest() == null ) {
            sale.setHarvest( Harvest.builder().build() );
        }
        saleResponseToHarvest1( saleDTO, sale.getHarvest() );
        sale.setSaleDate( saleDTO.getSaleDate() );
        sale.setUnitPrice( saleDTO.getUnitPrice() );
        sale.setQuantity( saleDTO.getQuantity() );
        sale.setClientName( saleDTO.getClientName() );
        sale.setTotalRevenue( saleDTO.getTotalRevenue() );
    }

    protected Harvest saleResponseToHarvest(SaleResponse saleResponse) {
        if ( saleResponse == null ) {
            return null;
        }

        Harvest.HarvestBuilder harvest = Harvest.builder();

        harvest.id( saleResponse.getHarvestId() );

        return harvest.build();
    }

    private Long saleHarvestId(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Harvest harvest = sale.getHarvest();
        if ( harvest == null ) {
            return null;
        }
        Long id = harvest.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void saleResponseToHarvest1(SaleResponse saleResponse, Harvest mappingTarget) {
        if ( saleResponse == null ) {
            return;
        }

        mappingTarget.setId( saleResponse.getHarvestId() );
    }
}
