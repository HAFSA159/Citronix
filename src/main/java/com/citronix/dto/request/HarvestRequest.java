package com.citronix.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
public class HarvestRequest {

    @NotNull(message = "Harvest date is required")
    @PastOrPresent(message = "Date must not be in the future")
    private LocalDate harvestDate;

    @NotNull(message = "Field ID is required")
    private Long fieldId;
}

