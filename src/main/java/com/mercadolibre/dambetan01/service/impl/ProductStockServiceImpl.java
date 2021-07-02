package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.ProductStockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductStockServiceImpl implements ProductStockService {
    private ProductStockRepository repository;

    public ProductStockServiceImpl(ProductStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductStock> saveAll(List<ProductStock> productStock) {
        return repository.saveAll(productStock);
    }
}
