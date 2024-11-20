package com.citronix.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SaleResponse {
    private Long id;
    private LocalDate saleDate;
    private Double unitPrice;
    private Double quantity;
    private String clientName;
    private Double totalRevenue;
    private Long harvestId;

}
