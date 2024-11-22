package com.citronix.service.impl;

import com.citronix.dto.request.TreeRequest;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Tree;
import com.citronix.entity.Field;
import com.citronix.mapper.TreeMapper;
import com.citronix.repository.TreeRepository;
import com.citronix.repository.FieldRepository;
import com.citronix.service.interfaces.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;
    private final TreeMapper treeMapper;

    @Autowired
    public TreeServiceImpl(TreeRepository treeRepository, FieldRepository fieldRepository, TreeMapper treeMapper) {
        this.treeRepository = treeRepository;
        this.fieldRepository = fieldRepository;
        this.treeMapper = treeMapper;
    }

    @Override
    public TreeResponse createTree(TreeRequest treeRequest) {
        Field field = fieldRepository.findById(treeRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        long currentTreeCount = treeRepository.countByFieldId(field.getId());
        double fieldAreaInHectares = field.getArea() / 10000.0;
        double currentTreeDensity = currentTreeCount / fieldAreaInHectares;

        if (currentTreeDensity >= 100) {
            throw new RuntimeException("Maximum tree density of 100 trees per hectare exceeded");
        }

        Tree tree = treeMapper.toEntity(treeRequest);
        tree.setField(field);
        Tree savedTree = treeRepository.save(tree);

        return setTreeMetricsAndMapToResponse(savedTree);
    }

    @Override
    public TreeResponse findTreeById(Long id) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));
        return setTreeMetricsAndMapToResponse(tree);
    }

    @Override
    public List<TreeResponse> getAllTrees() {
        List<Tree> trees = treeRepository.findAll();
        return trees.stream()
                .map(this::setTreeMetricsAndMapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TreeResponse updateTree(Long id, TreeRequest treeRequest) {
        Tree tree = treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found"));

        tree.setPlantingDate(treeRequest.getPlantingDate());
        Field field = fieldRepository.findById(treeRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        tree.setField(field);
        tree = treeRepository.save(tree);

        return setTreeMetricsAndMapToResponse(tree);
    }

    @Override
    public boolean deleteTree(Long id) {
        if (treeRepository.existsById(id)) {
            treeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private int calculateAge(LocalDate plantingDate) {
        if (plantingDate == null) {
            return 0;
        }
        LocalDate currentDate = LocalDate.now();
        return Period.between(plantingDate, currentDate).getYears();
    }

    private double calculateAnnualProductivity(int age) {
        if (age < 3) {
            return 2.5 * 4;
        } else if (age <= 10) {
            return 12.0 * 4;
        } else {
            return 20.0 * 4;
        }
    }

    private TreeResponse setTreeMetricsAndMapToResponse(Tree tree) {
        int age = calculateAge(tree.getPlantingDate());
        double annualProductivity = calculateAnnualProductivity(age);

        TreeResponse response = treeMapper.toDTO(tree);
        response.setAge(age);
        response.setAnnualProductivity(annualProductivity);

        return response;
    }
}
