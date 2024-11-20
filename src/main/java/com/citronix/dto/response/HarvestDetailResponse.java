package com.citronix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class HarvestDetailResponse {
    private Long id;
    private Double amount;
    private LocalDate date;
}
