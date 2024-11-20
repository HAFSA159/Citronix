package com.citronix.service.interfaces;

import com.citronix.dto.response.TreeResponse;
import com.citronix.dto.request.TreeRequest;
import com.citronix.entity.Tree;

import java.util.List;

public interface TreeService {

    TreeResponse findTreeById(Long id);
    List<TreeResponse> getAllTrees();
    TreeResponse createTree(TreeRequest treeRequest);
    TreeResponse updateTree(Long id, TreeRequest treeRequest);
    boolean deleteTree(Long id);
    TreeResponse mapToResponse(Tree tree);

}
