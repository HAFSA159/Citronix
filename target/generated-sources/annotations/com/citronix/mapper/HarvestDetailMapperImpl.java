package com.citronix.mapper;

import com.citronix.dto.response.HarvestDetailResponse;
import com.citronix.entity.Harvest;
import com.citronix.entity.HarvestDetail;
import com.citronix.entity.Tree;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-24T12:37:34+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class HarvestDetailMapperImpl implements HarvestDetailMapper {

    @Override
    public HarvestDetail toEntity(HarvestDetailResponse harvestDetailDTO) {
        if ( harvestDetailDTO == null ) {
            return null;
        }

        HarvestDetail.HarvestDetailBuilder harvestDetail = HarvestDetail.builder();

        harvestDetail.harvest( harvestDetailResponseToHarvest( harvestDetailDTO ) );
        harvestDetail.tree( harvestDetailResponseToTree( harvestDetailDTO ) );
        harvestDetail.id( harvestDetailDTO.getId() );
        harvestDetail.quantity( harvestDetailDTO.getQuantity() );

        return harvestDetail.build();
    }

    @Override
    public HarvestDetailResponse toDTO(HarvestDetail harvestDetail) {
        if ( harvestDetail == null ) {
            return null;
        }

        HarvestDetailResponse.HarvestDetailResponseBuilder harvestDetailResponse = HarvestDetailResponse.builder();

        harvestDetailResponse.harvestId( harvestDetailHarvestId( harvestDetail ) );
        harvestDetailResponse.treeId( harvestDetailTreeId( harvestDetail ) );
        harvestDetailResponse.id( harvestDetail.getId() );
        harvestDetailResponse.quantity( harvestDetail.getQuantity() );

        return harvestDetailResponse.build();
    }

    @Override
    public void updateEntityFromDTO(HarvestDetailResponse harvestDetailDTO, HarvestDetail harvestDetail) {
        if ( harvestDetailDTO == null ) {
            return;
        }

        if ( harvestDetail.getHarvest() == null ) {
            harvestDetail.setHarvest( Harvest.builder().build() );
        }
        harvestDetailResponseToHarvest1( harvestDetailDTO, harvestDetail.getHarvest() );
        if ( harvestDetail.getTree() == null ) {
            harvestDetail.setTree( Tree.builder().build() );
        }
        harvestDetailResponseToTree1( harvestDetailDTO, harvestDetail.getTree() );
        harvestDetail.setQuantity( harvestDetailDTO.getQuantity() );
    }

    protected Harvest harvestDetailResponseToHarvest(HarvestDetailResponse harvestDetailResponse) {
        if ( harvestDetailResponse == null ) {
            return null;
        }

        Harvest.HarvestBuilder harvest = Harvest.builder();

        harvest.id( harvestDetailResponse.getHarvestId() );

        return harvest.build();
    }

    protected Tree harvestDetailResponseToTree(HarvestDetailResponse harvestDetailResponse) {
        if ( harvestDetailResponse == null ) {
            return null;
        }

        Tree.TreeBuilder tree = Tree.builder();

        tree.id( harvestDetailResponse.getTreeId() );

        return tree.build();
    }

    private Long harvestDetailHarvestId(HarvestDetail harvestDetail) {
        if ( harvestDetail == null ) {
            return null;
        }
        Harvest harvest = harvestDetail.getHarvest();
        if ( harvest == null ) {
            return null;
        }
        Long id = harvest.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long harvestDetailTreeId(HarvestDetail harvestDetail) {
        if ( harvestDetail == null ) {
            return null;
        }
        Tree tree = harvestDetail.getTree();
        if ( tree == null ) {
            return null;
        }
        Long id = tree.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void harvestDetailResponseToHarvest1(HarvestDetailResponse harvestDetailResponse, Harvest mappingTarget) {
        if ( harvestDetailResponse == null ) {
            return;
        }

        mappingTarget.setId( harvestDetailResponse.getHarvestId() );
    }

    protected void harvestDetailResponseToTree1(HarvestDetailResponse harvestDetailResponse, Tree mappingTarget) {
        if ( harvestDetailResponse == null ) {
            return;
        }

        mappingTarget.setId( harvestDetailResponse.getTreeId() );
    }
}
