package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.inbound.PostProductStockDto;
import com.mercadolibre.dambetan01.dtos.inbound.ProductStockDto;

public class PostProductStockDTOMapper {
    public static ProductStockDto buildFrom(PostProductStockDto postProductStockDto){
        ProductStockDto dto = new ProductStockDto();
        dto.setProductId(postProductStockDto.getProductId());
        dto.setDueDate(postProductStockDto.getDueDate());
        dto.setInitialQuantity(postProductStockDto.getInitialQuantity());
        dto.setCurrentQuantity(postProductStockDto.getInitialQuantity());
        dto.setCurrentTemperature(postProductStockDto.getCurrentTemperature());
        dto.setMinimumTemperature(postProductStockDto.getMinimumTemperature());
        dto.setManufacturingDate(postProductStockDto.getManufacturingDate());
        dto.setManufacturingTime(postProductStockDto.getManufacturingTime());
        return dto;
    }
}
