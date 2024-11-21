package com.citronix.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeRequest {
    private Long fieldId;
    private LocalDate plantingDate;

}
