package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.ProductStock;

import java.util.List;

public interface ProductStockService {
    public List<ProductStock> saveAll(List<ProductStock> productStock);
}