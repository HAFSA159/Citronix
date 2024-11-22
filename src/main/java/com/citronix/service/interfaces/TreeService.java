package com.citronix.service.interfaces;

import com.citronix.dto.request.TreeRequest;
import com.citronix.dto.response.TreeResponse;

import java.util.List;

public interface TreeService {
    TreeResponse createTree(TreeRequest treeRequest);
    TreeResponse findTreeById(Long id);
    List<TreeResponse> getAllTrees();
    TreeResponse updateTree(Long id, TreeRequest treeRequest);
    boolean deleteTree(Long id);

}
