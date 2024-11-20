package com.citronix.service.impl;

import com.citronix.dto.request.SaleRequest;
import com.citronix.dto.response.SaleResponse;
import com.citronix.entity.Sale;
import com.citronix.entity.Harvest;
import com.citronix.repository.SaleRepository;
import com.citronix.repository.HarvestRepository;
import com.citronix.service.interfaces.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final HarvestRepository harvestRepository;

    public SaleServiceImpl(SaleRepository saleRepository, HarvestRepository harvestRepository) {
        this.saleRepository = saleRepository;
        this.harvestRepository = harvestRepository;
    }

    @Override
    public SaleResponse findSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return mapToResponse(sale);
    }

    @Override
    public List<SaleResponse> findAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public SaleResponse createSale(SaleRequest saleRequest) {
        Harvest harvest = harvestRepository.findById(saleRequest.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        Sale sale = Sale.builder()
                .saleDate(saleRequest.getSaleDate())
                .unitPrice(saleRequest.getUnitPrice())
                .quantity(saleRequest.getQuantity())
                .clientName(saleRequest.getClientName())
                .harvest(harvest)
                .build();

        sale = saleRepository.save(sale);
        return mapToResponse(sale);
    }

    @Override
    public SaleResponse updateSale(Long id, SaleRequest saleRequest) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        Harvest harvest = harvestRepository.findById(saleRequest.getHarvestId())
                .orElseThrow(() -> new RuntimeException("Harvest not found"));

        sale.setSaleDate(saleRequest.getSaleDate());
        sale.setUnitPrice(saleRequest.getUnitPrice());
        sale.setQuantity(saleRequest.getQuantity());
        sale.setClientName(saleRequest.getClientName());
        sale.setHarvest(harvest);

        sale = saleRepository.save(sale);
        return mapToResponse(sale);
    }

    @Override
    public boolean deleteSale(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SaleResponse mapToResponse(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .saleDate(sale.getSaleDate())
                .unitPrice(sale.getUnitPrice())
                .quantity(sale.getQuantity())
                .clientName(sale.getClientName())
                .totalRevenue(sale.getTotalRevenue())
                .harvestId(sale.getHarvest().getId())
                .build();
    }

}
