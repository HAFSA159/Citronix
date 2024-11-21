package com.citronix.controller;

import com.citronix.dto.request.HarvestDetailRequest;
import com.citronix.dto.response.HarvestDetailResponse;
import com.citronix.service.interfaces.HarvestDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/harvest-details")
public class HarvestDetailController {

    private final HarvestDetailService harvestDetailService;

    public HarvestDetailController(HarvestDetailService harvestDetailService) {
        this.harvestDetailService = harvestDetailService;
    }


    @PostMapping
    public ResponseEntity<HarvestDetailResponse> create(@RequestBody HarvestDetailRequest request) {
        return new ResponseEntity<>(harvestDetailService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HarvestDetailResponse>> getAll() {
        return ResponseEntity.ok(harvestDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(harvestDetailService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestDetailResponse> update(@PathVariable Long id, @RequestBody HarvestDetailRequest request) {
        return ResponseEntity.ok(harvestDetailService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (harvestDetailService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
