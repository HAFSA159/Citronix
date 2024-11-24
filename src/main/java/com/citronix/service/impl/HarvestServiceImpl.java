package com.citronix.service.impl;

import com.citronix.dto.request.HarvestRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.dto.response.HarvestResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.Harvest;
import com.citronix.entity.enums.Season;
import com.citronix.mapper.FarmMapper;
import com.citronix.mapper.HarvestMapper;
import com.citronix.repository.HarvestRepository;
import com.citronix.service.interfaces.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HarvestServiceImpl implements HarvestService {

    private final FarmMapper farmMapper;
    private final HarvestRepository harvestRepository;
    private final HarvestMapper harvestMapper;

    @Autowired
    public HarvestServiceImpl(HarvestRepository harvestRepository, HarvestMapper harvestMapper,FarmMapper farmMapper) {
        this.harvestRepository = harvestRepository;
        this.harvestMapper = harvestMapper;
        this.farmMapper = farmMapper;
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
        Season season = determineSeason(harvestRequest.getHarvestDate());

        Harvest harvest = Harvest.builder()
                .harvestDate(harvestRequest.getHarvestDate())
                .season(season)
                .totalQuantity(0.0)
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

        Season season = determineSeason(harvestRequest.getHarvestDate());

        harvest.setHarvestDate(harvestRequest.getHarvestDate());
        harvest.setSeason(season);

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

    public List<FarmResponse> getFarmsHarvestedInWinter() {
                List<Farm> farms = harvestRepository.findAll().stream()
                .filter(harvest -> harvest.getSeason() == Season.WINTER)
                .flatMap(harvest -> harvest.getHarvestDetails().stream())
                .map(harvestDetail -> harvestDetail.getTree().getField().getFarm())
                .distinct().collect(Collectors.toList());

        return farms.stream().map(farmMapper::toDTO).collect(Collectors.toList());

    }
}
