package com.citronix.controller;

import com.citronix.dto.request.FarmRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.entity.Farm;
import com.citronix.service.interfaces.FarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmResponse> createFarm(@Valid @RequestBody FarmRequest request) {
        return ResponseEntity.ok(farmService.createFarm(request));
    }

    @GetMapping
    public ResponseEntity<List<FarmResponse>> getAllFarms() {
        List<FarmResponse> farms = farmService.getAllFarms();
        return ResponseEntity.ok(farms);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarm(@PathVariable Long id) {
        return ResponseEntity.ok(farmService.getFarmById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponse> updateFarm(@PathVariable Long id, @Valid @RequestBody FarmRequest request) {
        FarmResponse updatedFarm = farmService.updateFarm(id, request);
        return ResponseEntity.ok(updatedFarm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validateFieldsArea")
    public ResponseEntity<Boolean> validateFieldsArea(@Valid @RequestBody Farm farm) {
        boolean isValid = farmService.validateFieldsArea(farm);
        return ResponseEntity.ok(isValid);
    }


}
