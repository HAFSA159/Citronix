package com.citronix.service.impl;

import com.citronix.dto.request.HarvestRequest;
import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.HarvestRepository;
import com.citronix.service.interfaces.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldRepository fieldRepository;

    @Autowired
    public HarvestServiceImpl(HarvestRepository harvestRepository, FieldRepository fieldRepository) {
        this.harvestRepository = harvestRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public HarvestResponse createHarvest(HarvestRequest harvestRequest) {
        Field field = fieldRepository.findById(harvestRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        Harvest harvest = Harvest.builder()
                .harvestDate(harvestRequest.getHarvestDate())
                .season(Season.valueOf(harvestRequest.getSeason().toUpperCase()))
                .totalQuantity(harvestRequest.getTotalQuantity())
                .field(field)
                .build();
        harvest = harvestRepository.save(harvest);
        return mapToResponse(harvest);
    }

    @Override
    public HarvestResponse findHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        return mapToResponse(harvest);
    }

    @Override
    public List<HarvestResponse> findAllHarvests() {
        return harvestRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    @Override
    public HarvestResponse updateHarvest(Long id, HarvestRequest harvestRequest) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        Field field = fieldRepository.findById(harvestRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));
        harvest.setHarvestDate(harvestRequest.getHarvestDate());
        harvest.setSeason(Season.valueOf(harvestRequest.getSeason().toUpperCase()));
        harvest.setTotalQuantity(harvestRequest.getTotalQuantity());
        harvest.setField(field);
        harvest = harvestRepository.save(harvest);
        return mapToResponse(harvest);
    }

    @Override
    public boolean deleteHarvest(Long id) {
        if (harvestRepository.existsById(id)) {
            harvestRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private HarvestResponse mapToResponse(Harvest harvest) {
        return new HarvestResponse(
                harvest.getId(),
                harvest.getHarvestDate(),
                harvest.getSeason().name(),
                harvest.getTotalQuantity(),
                harvest.getField().getId()
        );
    }
}
