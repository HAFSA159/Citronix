package com.citronix.dto.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FarmRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Area is required")
    private Double area;

    @NotBlank(message = "Location is required")
    private String location;
}
