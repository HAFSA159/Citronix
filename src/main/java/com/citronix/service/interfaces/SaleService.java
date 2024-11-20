package com.citronix.service.interfaces;

import com.citronix.dto.request.SaleRequest;
import com.citronix.dto.response.SaleResponse;

import java.util.List;

public interface SaleService {
    SaleResponse findSaleById(Long id);
    List<SaleResponse> findAllSales();
    SaleResponse createSale(SaleRequest saleRequest);
    SaleResponse updateSale(Long id, SaleRequest saleRequest);
    boolean deleteSale(Long id);
}
