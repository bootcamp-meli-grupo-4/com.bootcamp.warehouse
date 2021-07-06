package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductStockMapper implements MapperDtoToModel<ProductStock, ProductStockDto>,
        MapperModelToDto<ProductStock, ProductStockDto>{

    private final ProductService productService;


    @Override
    public ProductStock dtoToModel(ProductStockDto dto) {
        ProductStock productStock = new ProductStock();
        if(dto.getBatchNumber() != null){
            productStock.setId(dto.getBatchNumber());
        }
        productStock.setProduct(productService.findById(dto.getProductId()));
        productStock.setCurrentTemperature(dto.getCurrentTemperature());
        productStock.setDueDate(dto.getDueDate());
        productStock.setInitialQuantity(dto.getInitialQuantity());
        productStock.setCurrentQuantity(dto.getCurrentQuantity());
        productStock.setManufacturingDate(dto.getManufacturingDate());
        productStock.setMinimumTemperature(dto.getMinimumTemperature());
        productStock.setManufacturingTime(dto.getManufacturingTime());
        return productStock;
    }



    @Override
    public ProductStockDto modelToDto(ProductStock model) {
        ProductStockDto dto = new ProductStockDto();
        dto.setBatchNumber(model.getId());
        dto.setProductId(model.getProduct().getId());
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
