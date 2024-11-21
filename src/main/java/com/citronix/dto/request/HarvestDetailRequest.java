package com.citronix.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HarvestDetailRequest {
    @NotNull(message = "Quantity is required")
    private Double quantity;

    @NotNull(message = "Harvest ID is required")
    private Long harvestId;

    @NotNull(message = "Tree ID is required")
    private Long treeId;
}
