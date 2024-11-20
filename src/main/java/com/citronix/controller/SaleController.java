package com.citronix.controller;

import com.citronix.dto.request.SaleRequest;
import com.citronix.dto.response.SaleResponse;
import com.citronix.service.interfaces.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponse> createSale(@RequestBody SaleRequest saleRequest) {
        SaleResponse saleResponse = saleService.createSale(saleRequest);
        return new ResponseEntity<>(saleResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {
        SaleResponse saleResponse = saleService.findSaleById(id);
        return new ResponseEntity<>(saleResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        List<SaleResponse> saleResponses = saleService.findAllSales();
        return new ResponseEntity<>(saleResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long id, @RequestBody SaleRequest saleRequest) {
        SaleResponse saleResponse = saleService.updateSale(id, saleRequest);
        return new ResponseEntity<>(saleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        boolean isDeleted = saleService.deleteSale(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
