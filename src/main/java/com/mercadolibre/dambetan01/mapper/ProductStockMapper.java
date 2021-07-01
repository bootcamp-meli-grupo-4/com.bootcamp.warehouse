package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.service.ProductService;

public class ProductStockMapper implements Mapper<ProductStock, ProductStockDto>{
    private ProductService productService;

    public ProductStockMapper(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductStock dtoToModel(ProductStockDto dto) {
        ProductStock productStock = new ProductStock();
        productStock.setProduct(productService.findById(dto.getProductId()));
        productStock.setCurrentQuantity(dto.getCurrentQuantity());
        productStock.setCurrentTemperature(dto.getCurrentTemperature());
        productStock.setDueDate(dto.getDueDate());
        productStock.setInitialQuantity(dto.getInitialQuantity());
        productStock.setManufacturingDate(dto.getManufacturingDate());
        productStock.setMinimumTemperature(dto.getMinimumTemperature());
        return productStock;
    }

    @Override
    public ProductStockDto modelToDto(ProductStock model) {
        return null;
    }
}
