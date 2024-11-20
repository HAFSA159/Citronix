package com.citronix.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class FieldResponse {

    private Long id;
    private Double area;
    private Long farmId;
    private List<TreeResponse> trees;
    private List<Long> harvestIds;

    public FieldResponse(Long id, Double area, Long farmId, List<TreeResponse> trees) {
        this.id = id;
        this.area = area;
        this.farmId = farmId;
        this.trees = trees;
    }
}
