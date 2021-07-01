package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.model.ProductStock;
import org.springframework.stereotype.Component;

@Component
public class ProductStockResponseMapper implements MapperModelToDto<ProductStock, ProductStockResponseDto>{

    @Override
    public ProductStockResponseDto modelToDto(ProductStock model) {
        ProductStockResponseDto dto = new ProductStockResponseDto();
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
