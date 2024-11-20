package com.citronix.service.impl;

import com.citronix.dto.request.TreeRequest;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Tree;
import com.citronix.entity.Field;
import com.citronix.repository.TreeRepository;
import com.citronix.repository.FieldRepository;
import com.citronix.service.interfaces.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public TreeServiceImpl(TreeRepository treeRepository, FieldRepository fieldRepository) {
        this.treeRepository = treeRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public TreeResponse createTree(TreeRequest treeRequest) {
        Field field = fieldRepository.findById(treeRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        Tree tree = new Tree(null, treeRequest.getPlantingDate(), treeRequest.isProductive(), field, null);
        tree = treeRepository.save(tree);
        return mapToResponse(tree);
    }


    @Override
    public TreeResponse findTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        return mapToResponse(tree);
    }

    @Override
    public List<TreeResponse> getAllTrees() {
        List<Tree> trees = treeRepository.findAll();
        return trees.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public TreeResponse updateTree(Long id, TreeRequest treeRequest) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        tree.setPlantingDate(treeRequest.getPlantingDate());
        tree.setProductive(treeRequest.isProductive());
        Field field = fieldRepository.findById(treeRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        tree.setField(field);
        tree = treeRepository.save(tree);
        return mapToResponse(tree);
    }

    @Override
    public boolean deleteTree(Long id) {
        if (treeRepository.existsById(id)) {
            treeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TreeResponse mapToResponse(Tree tree) {
        return new TreeResponse(
                tree.getId(),
                tree.getPlantingDate(),
                tree.isProductive(),
                null
        );
    }
}
