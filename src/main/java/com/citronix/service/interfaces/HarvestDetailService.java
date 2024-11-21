package com.citronix.service.interfaces;

import com.citronix.dto.request.HarvestDetailRequest;
import com.citronix.dto.response.HarvestDetailResponse;

import java.util.List;

public interface HarvestDetailService {
    HarvestDetailResponse findById(Long id);
    List<HarvestDetailResponse> findAll();
    HarvestDetailResponse create(HarvestDetailRequest request);
    HarvestDetailResponse update(Long id, HarvestDetailRequest request);
    boolean delete(Long id);
}
