package com.citronix.controller;

import com.citronix.dto.request.HarvestRequest;
import com.citronix.dto.response.FarmResponse;
import com.citronix.dto.response.HarvestResponse;
import com.citronix.service.interfaces.HarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/harvests")
public class HarvestController {

    private final HarvestService harvestService;

    public HarvestController(HarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @PostMapping
    public ResponseEntity<HarvestResponse> createHarvest(@Valid @RequestBody HarvestRequest harvestRequest) {
        HarvestResponse createdHarvest = harvestService.createHarvest(harvestRequest);
        return new ResponseEntity<>(createdHarvest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HarvestResponse>> getAllHarvests() {
        return ResponseEntity.ok(harvestService.findAllHarvests());
    }

    @GetMapping("/winter")
    public ResponseEntity<List<FarmResponse>> getFarmsHarvestedInWinter() {
        return ResponseEntity.ok(harvestService.getFarmsHarvestedInWinter());
    }


    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponse> getHarvestById(@PathVariable Long id) {
        return ResponseEntity.ok(harvestService.findHarvestById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponse> updateHarvest(@PathVariable Long id, @Valid @RequestBody HarvestRequest harvestRequest) {
        HarvestResponse updatedHarvest = harvestService.updateHarvest(id, harvestRequest);
        return ResponseEntity.ok(updatedHarvest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHarvest(@PathVariable Long id) {
        boolean deleted = harvestService.deleteHarvest(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
