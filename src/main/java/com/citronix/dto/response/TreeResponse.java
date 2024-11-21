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
    private boolean isProductive;
    private int age;
    private List<HarvestDetailResponse> harvestDetails;

    public TreeResponse(Long id) {
        this.id = id;
        this.plantingDate = null;
        this.isProductive = false;
        this.harvestDetails = null;
        this.age = 0;
    }
}
