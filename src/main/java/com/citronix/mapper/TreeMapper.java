package com.citronix.mapper;

import com.citronix.dto.request.TreeRequest;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TreeMapper {

    @Mapping(source="fieldId",target="field.id")
    Tree toEntity(TreeRequest treeDTO);

    @Mapping(source="field.id",target="fieldId")
    TreeResponse toDTO(Tree tree);

    @Mapping(target="id", ignore=true)
    @Mapping(source="fieldId",target="field.id")
    void updateEntityFromDTO(TreeResponse treeDTO, @MappingTarget Tree tree);
}
