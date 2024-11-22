package com.citronix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResponse {
    private Long id;
    private LocalDate plantingDate;
    private int age;
    private Long fieldId;
    private double annualProductivity;


    public TreeResponse(Long id ) {
        this.id = id;

    }
}