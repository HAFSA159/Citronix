package com.citronix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HarvestResponse {
    private Long id;
    private LocalDate harvestDate;
    private String season;
    private Double totalQuantity;
    private Long fieldId;
}
