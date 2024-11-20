package com.citronix.service.interfaces;

import com.citronix.dto.request.FarmRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import com.citronix.entity.enums.Season;

import java.util.List;

public interface FarmService {
    FarmResponse createFarm(FarmRequest request);
    FarmResponse getFarmById(Long id);
    List<FarmResponse> getAllFarms();
    FarmResponse updateFarm(Long id, FarmRequest request);
    void deleteFarm(Long id);
    boolean validateFieldsArea(Farm farm);
    double calculateTotalProductivity(Long farmId, Season season);
}