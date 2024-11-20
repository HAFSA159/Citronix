package com.citronix.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SaleRequest {
    @NotNull(message = "Sale date is required")
    private LocalDate saleDate;

    @NotNull(message = "Unit price is required")
    private Double unitPrice;

    @NotNull(message = "Quantity is required")
    private Double quantity;

    @NotBlank(message = "Client name is required")
    private String clientName;

    @NotNull(message = "Harvest ID is required")
    private Long harvestId;
}
