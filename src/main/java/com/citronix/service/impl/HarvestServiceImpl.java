package com.citronix.service.impl;

import com.citronix.dto.request.HarvestRequest;
import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Field;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import com.citronix.mapper.HarvestMapper;
import com.citronix.repository.FieldRepository;
import com.citronix.repository.HarvestRepository;
import com.citronix.service.interfaces.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldRepository fieldRepository;
    private final HarvestMapper harvestMapper;

    @Autowired
    public HarvestServiceImpl(HarvestRepository harvestRepository, FieldRepository fieldRepository, HarvestMapper harvestMapper) {
        this.harvestRepository = harvestRepository;
        this.fieldRepository = fieldRepository;
        this.harvestMapper = harvestMapper;
    }

    private Season determineSeason(LocalDate date) {
        Month month = date.getMonth();
        switch (month) {
            case DECEMBER: case JANUARY: case FEBRUARY:
                return Season.WINTER;
            case MARCH: case APRIL: case MAY:
                return Season.SPRING;
            case JUNE: case JULY: case AUGUST:
                return Season.SUMMER;
            case SEPTEMBER: case OCTOBER: case NOVEMBER:
                return Season.AUTUMN;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }


    @Override
    public HarvestResponse createHarvest(HarvestRequest harvestRequest) {
        Field field = fieldRepository.findById(harvestRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        Season season = determineSeason(harvestRequest.getHarvestDate());

        boolean harvestExists = harvestRepository.existsByFieldAndSeason(field, season);
        if (harvestExists) {
            throw new RuntimeException("A harvest has already been performed for this field in the " + season + " season.");
        }

        Harvest harvest = Harvest.builder()
                .harvestDate(harvestRequest.getHarvestDate())
                .season(season)
                .totalQuantity(harvestRequest.getTotalQuantity())
                .field(field)
                .build();

        harvest = harvestRepository.save(harvest);

        return harvestMapper.toDTO(harvest);
    }


    @Override
    public HarvestResponse findHarvestById(Long id) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found"));
        return harvestMapper.toDTO(harvest);
    }

    @Override
    public List<HarvestResponse> findAllHarvests() {
        return harvestRepository.findAll().stream()
                .map(harvestMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HarvestResponse updateHarvest(Long id, HarvestRequest harvestRequest) {
        Harvest harvest = harvestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        Field field = fieldRepository.findById(harvestRequest.getFieldId())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        Season season = determineSeason(harvestRequest.getHarvestDate());

        harvest.setHarvestDate(harvestRequest.getHarvestDate());
        harvest.setSeason(season);
        harvest.setTotalQuantity(harvestRequest.getTotalQuantity());
        harvest.setField(field);

        harvest = harvestRepository.save(harvest);
        return harvestMapper.toDTO(harvest);
    }

    @Override
    public boolean deleteHarvest(Long id) {
        if (harvestRepository.existsById(id)) {
            harvestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
