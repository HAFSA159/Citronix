package com.citronix.mapper;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.Field;
import com.citronix.entity.Tree;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-22T12:18:19+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class FieldMapperImpl implements FieldMapper {

    @Override
    public FieldResponse toDTO(Field field) {
        if ( field == null ) {
            return null;
        }

        Long farmId = null;
        Long id = null;
        Double area = null;
        List<TreeResponse> trees = null;

        farmId = fieldFarmId( field );
        id = field.getId();
        area = field.getArea();
        trees = treeListToTreeResponseList( field.getTrees() );

        FieldResponse fieldResponse = new FieldResponse( id, area, farmId, trees );

        return fieldResponse;
    }

    @Override
    public Field toEntity(FieldRequest fieldRequest) {
        if ( fieldRequest == null ) {
            return null;
        }

        Field.FieldBuilder field = Field.builder();

        field.farm( fieldRequestToFarm( fieldRequest ) );
        field.area( fieldRequest.getArea() );

        return field.build();
    }

    @Override
    public void updateEntityFromDTO(FieldResponse fieldResponse, Field field) {
        if ( fieldResponse == null ) {
            return;
        }

        field.setArea( fieldResponse.getArea() );
        if ( field.getTrees() != null ) {
            List<Tree> list = treeResponseListToTreeList( fieldResponse.getTrees() );
            if ( list != null ) {
                field.getTrees().clear();
                field.getTrees().addAll( list );
            }
            else {
                field.setTrees( null );
            }
        }
        else {
            List<Tree> list = treeResponseListToTreeList( fieldResponse.getTrees() );
            if ( list != null ) {
                field.setTrees( list );
            }
        }
    }

    private Long fieldFarmId(Field field) {
        if ( field == null ) {
            return null;
        }
        Farm farm = field.getFarm();
        if ( farm == null ) {
            return null;
        }
        Long id = farm.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TreeResponse treeToTreeResponse(Tree tree) {
        if ( tree == null ) {
            return null;
        }

        TreeResponse treeResponse = new TreeResponse();

        treeResponse.setId( tree.getId() );
        treeResponse.setPlantingDate( tree.getPlantingDate() );

        return treeResponse;
    }

    protected List<TreeResponse> treeListToTreeResponseList(List<Tree> list) {
        if ( list == null ) {
            return null;
        }

        List<TreeResponse> list1 = new ArrayList<TreeResponse>( list.size() );
        for ( Tree tree : list ) {
            list1.add( treeToTreeResponse( tree ) );
        }

        return list1;
    }

    protected Farm fieldRequestToFarm(FieldRequest fieldRequest) {
        if ( fieldRequest == null ) {
            return null;
        }

        Farm.FarmBuilder farm = Farm.builder();

        farm.id( fieldRequest.getFarmId() );

        return farm.build();
    }

    protected Tree treeResponseToTree(TreeResponse treeResponse) {
        if ( treeResponse == null ) {
            return null;
        }

        Tree.TreeBuilder tree = Tree.builder();

        tree.id( treeResponse.getId() );
        tree.plantingDate( treeResponse.getPlantingDate() );

        return tree.build();
    }

    protected List<Tree> treeResponseListToTreeList(List<TreeResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<Tree> list1 = new ArrayList<Tree>( list.size() );
        for ( TreeResponse treeResponse : list ) {
            list1.add( treeResponseToTree( treeResponse ) );
        }

        return list1;
    }
}
