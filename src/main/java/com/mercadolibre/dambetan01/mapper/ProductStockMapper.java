package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductStockMapper implements MapperDtoToModel<ProductStock, ProductStockDto>,
        MapperModelToDto<ProductStock, ProductStockDto>{
    private ProductService productService;

    public ProductStockMapper(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductStock dtoToModel(ProductStockDto dto) {
        ProductStock productStock = new ProductStock();
        if(dto.getBatchNumber() != null){
            productStock.setId(dto.getBatchNumber());
        }
        productStock.setProduct(productService.findById(dto.getProductId()));
        productStock.setCurrentQuantity(dto.getCurrentQuantity());
        productStock.setCurrentTemperature(dto.getCurrentTemperature());
        productStock.setDueDate(dto.getDueDate());
        productStock.setInitialQuantity(dto.getInitialQuantity());
        productStock.setManufacturingDate(dto.getManufacturingDate());
        productStock.setMinimumTemperature(dto.getMinimumTemperature());
        productStock.setManufacturingTime(dto.getManufacturingTime());
        return productStock;
    }



    @Override
    public ProductStockDto modelToDto(ProductStock model) {
        ProductStockDto dto = new ProductStockDto();
        dto.setProductId(model.getId());
        dto.setDueDate(model.getDueDate());
        dto.setCurrentQuantity(model.getCurrentQuantity());
        dto.setCurrentTemperature(model.getCurrentTemperature());
        dto.setInitialQuantity(model.getInitialQuantity());
        dto.setManufacturingDate(model.getManufacturingDate());
        dto.setMinimumTemperature(model.getMinimumTemperature());
        dto.setManufacturingTime(model.getManufacturingTime());
        return dto;
    }
}
