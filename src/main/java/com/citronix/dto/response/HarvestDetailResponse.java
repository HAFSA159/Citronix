package com.citronix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class HarvestDetailResponse {
    private Long id;
    private Double quantity;
    private Long harvestId;
    private Long treeId;

}

