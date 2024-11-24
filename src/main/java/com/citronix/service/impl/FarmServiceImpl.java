package com.citronix.service.impl;

import com.citronix.dto.request.FarmRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.enums.Season;
import com.citronix.mapper.FarmMapper;
import com.citronix.repository.FarmRepository;
import com.citronix.service.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Autowired
    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    @Override
    public FarmResponse createFarm(FarmRequest request) {
        Farm farm = Farm.builder()
                .name(request.getName())
                .area(request.getArea())
                .location(request.getLocation())
                .build();

        Farm savedFarm = farmRepository.save(farm);
        return farmMapper.toDTO(savedFarm);
    }

    @Override
    public FarmResponse getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm with ID " + id + " not found"));

        return farmMapper.toDTO(farm);
    }

    @Override
    public List<FarmResponse> getAllFarms() {
        List<Farm> farms = farmRepository.findAll();
        return farms.stream()
                .map(farmMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public FarmResponse updateFarm(Long id, FarmRequest request) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm with ID " + id + " not found"));

        existingFarm.setName(request.getName());
        existingFarm.setArea(request.getArea());
        existingFarm.setLocation(request.getLocation());

        Farm updatedFarm = farmRepository.save(existingFarm);
        return farmMapper.toDTO(updatedFarm);
    }

    @Override
    public void deleteFarm(Long id) {
        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm with ID " + id + " not found");
        }
        farmRepository.deleteById(id);
    }

    @Override
    public boolean validateFieldsArea(Farm farm) {
        double totalFieldsArea = farm.getFields().stream()
                .mapToDouble(field -> field.getArea())
                .sum();

        return totalFieldsArea <= farm.getArea();
    }

    @Override
    public double calculateTotalProductivity(Long farmId, Season season) {
        return 0;
    }






}
