package com.citronix.dto.request;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FieldRequest {

    @NotNull(message = "Area is required")
    private Double area;

    @NotNull(message = "Farm ID is required")
    private Long farmId;
}

