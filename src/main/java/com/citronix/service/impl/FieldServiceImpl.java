package com.citronix.service.impl;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.dto.response.TreeResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Farm;
import com.citronix.entity.Tree;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.FarmRepository;
import com.citronix.service.interfaces.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, FarmRepository farmRepository) {
        this.fieldRepository = fieldRepository;
        this.farmRepository = farmRepository;
    }

    @Override
    public FieldResponse createField(FieldRequest request) {
        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm with ID " + request.getFarmId() + " not found"));

        Field field = Field.builder()
                .area(request.getArea())
                .farm(farm)
                .build();

        Field savedField = fieldRepository.save(field);
        return mapToResponse(savedField);
    }

    @Override
    public FieldResponse getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field with ID " + id + " not found"));

        return mapToResponse(field);
    }

    @Override
    public List<FieldResponse> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        return fields.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FieldResponse updateField(Long id, FieldRequest request) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field with ID " + id + " not found"));

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm with ID " + request.getFarmId() + " not found"));

        existingField.setArea(request.getArea());
        existingField.setFarm(farm);

        Field updatedField = fieldRepository.save(existingField);
        return mapToResponse(updatedField);
    }

    @Override
    public void deleteField(Long id) {
        if (!fieldRepository.existsById(id)) {
            throw new RuntimeException("Field with ID " + id + " not found");
        }
        fieldRepository.deleteById(id);
    }

    private FieldResponse mapToResponse(Field field) {
        List<TreeResponse> treeResponses = (field.getTrees() != null)
                ? field.getTrees().stream()
                .map(tree -> new TreeResponse(tree.getId()))
                .collect(Collectors.toList())
                : Collections.emptyList();
        return new FieldResponse(
                field.getId(),
                field.getArea(),
                field.getFarm().getId(),
                treeResponses
        );
    }




}