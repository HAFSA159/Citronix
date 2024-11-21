package com.citronix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeResponse {
    private Long id;
    private LocalDate plantingDate;
    private int age;
    private double annualProductivity;


    public TreeResponse(Long id) {
        this.id = id;
        this.plantingDate = null;
        this.age = 0;
        this.annualProductivity = 0;
    }
}
