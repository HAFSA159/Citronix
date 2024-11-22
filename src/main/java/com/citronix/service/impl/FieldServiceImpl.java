package com.citronix.service.impl;

import com.citronix.dto.request.FieldRequest;
import com.citronix.dto.response.FieldResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Farm;
import com.citronix.mapper.FieldMapper;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.FarmRepository;
import com.citronix.service.interfaces.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;
    private final FieldMapper fieldMapper;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, FarmRepository farmRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.farmRepository = farmRepository;
        this.fieldMapper = fieldMapper;
    }


    @Override
    public FieldResponse createField(FieldRequest request) {
        if (request.getArea() < 0.1) {
            throw new RuntimeException("Field area must be at least 0.1 units.");
        }

        Farm farm = farmRepository.findById(request.getFarmId())
                .orElseThrow(() -> new RuntimeException("Farm with ID " + request.getFarmId() + " not found"));

        long currentFieldCount = fieldRepository.countByFarmId(farm.getId());
        if (currentFieldCount >= 10) {
            throw new RuntimeException("Farm cannot have more than 10 fields");
        }

        double currentFieldArea = fieldRepository.sumFieldAreaByFarmId(farm.getId());
        double maxAllowedArea = farm.getArea() * 0.5;
        double remainingArea = maxAllowedArea - currentFieldArea;

        if (request.getArea() > remainingArea) {
            throw new RuntimeException(String.format(
                    "Cannot add field. Remaining available area is %.2f units. Requested field area is %.2f units.",
                    remainingArea,
                    request.getArea()
            ));
        }

        Field field = Field.builder()
                .area(request.getArea())
                .farm(farm)
                .build();

        Field savedField = fieldRepository.save(field);
        return fieldMapper.toDTO(savedField);
    }

    @Override
    public FieldResponse getFieldById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Field with ID " + id + " not found"));

        return fieldMapper.toDTO(field);
    }

    @Override
    public List<FieldResponse> getAllFields() {
        List<Field> fields = fieldRepository.findAll();
        return fields.stream()
                .map(fieldMapper::toDTO)
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
        return fieldMapper.toDTO(updatedField);
    }

    @Override
    public void deleteField(Long id) {
        if (!fieldRepository.existsById(id)) {
            throw new RuntimeException("Field with ID " + id + " not found");
        }
        fieldRepository.deleteById(id);
    }
}
