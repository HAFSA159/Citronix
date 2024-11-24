package com.citronix.service.interfaces;

import com.citronix.dto.request.HarvestRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.dto.response.HarvestResponse;

import java.util.List;

public interface HarvestService {
    HarvestResponse findHarvestById(Long id);
    List<HarvestResponse> findAllHarvests();
    HarvestResponse createHarvest(HarvestRequest harvestRequest);
    HarvestResponse updateHarvest(Long id, HarvestRequest harvestRequest);
    boolean deleteHarvest(Long id);
    List<FarmResponse> getFarmsHarvestedInWinter();
}
