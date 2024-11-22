package com.citronix.mapper;

import com.citronix.dto.request.TreeRequest;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Tree;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T11:56:19+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class TreeMapperImpl implements TreeMapper {

    @Override
    public Tree toEntity(TreeRequest treeDTO) {
        if ( treeDTO == null ) {
            return null;
        }

        Tree.TreeBuilder tree = Tree.builder();

        tree.field( treeRequestToField( treeDTO ) );
        tree.plantingDate( treeDTO.getPlantingDate() );

        return tree.build();
    }

    @Override
    public TreeResponse toDTO(Tree tree) {
        if ( tree == null ) {
            return null;
        }

        TreeResponse treeResponse = new TreeResponse();

        treeResponse.setFieldId( treeFieldId( tree ) );
        treeResponse.setId( tree.getId() );
        treeResponse.setPlantingDate( tree.getPlantingDate() );

        return treeResponse;
    }

    @Override
    public void updateEntityFromDTO(TreeResponse treeDTO, Tree tree) {
        if ( treeDTO == null ) {
            return;
        }

        if ( tree.getField() == null ) {
            tree.setField( Field.builder().build() );
        }
        treeResponseToField( treeDTO, tree.getField() );
        tree.setPlantingDate( treeDTO.getPlantingDate() );
    }

    protected Field treeRequestToField(TreeRequest treeRequest) {
        if ( treeRequest == null ) {
            return null;
        }

        Field.FieldBuilder field = Field.builder();

        field.id( treeRequest.getFieldId() );

        return field.build();
    }

    private Long treeFieldId(Tree tree) {
        if ( tree == null ) {
            return null;
        }
        Field field = tree.getField();
        if ( field == null ) {
            return null;
        }
        Long id = field.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected void treeResponseToField(TreeResponse treeResponse, Field mappingTarget) {
        if ( treeResponse == null ) {
            return;
        }

        mappingTarget.setId( treeResponse.getFieldId() );
    }
}
