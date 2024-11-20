package com.citronix.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class HarvestRequest {
    @NotNull(message = "Harvest date is required")
    private LocalDate harvestDate;

    @NotNull(message = "Season is required")
    private String season;

    @NotNull(message = "Total quantity is required")
    private Double totalQuantity;

    @NotNull(message = "Field ID is required")
    private Long fieldId;
}
