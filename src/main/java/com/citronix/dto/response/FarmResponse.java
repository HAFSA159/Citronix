package com.citronix.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FarmResponse {
    private Long id;
    private String name;
    private Double area;
    private String location;
    private LocalDate creationDate;


}
