package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.mapper.ProductStockResponseMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.ProductStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductStockResponseMapperTest {
    private ProductStockResponseMapper mapper;

    @BeforeEach
    public void setUp(){
        mapper = new ProductStockResponseMapper();
    }

    @Test
    public void shouldConvertModelToDtoCorrectly(){
        ProductStock model = new ProductStock();
        Product p = new Product();
        p.setId(1L);
        model.setProduct(p);
        model.setDueDate(LocalDate.now());
        model.setCurrentQuantity(12);
        model.setCurrentTemperature(32d);
        model.setManufacturingDate(LocalDate.now());
        model.setMinimumTemperature(1d);
        model.setManufacturingTime(LocalDateTime.now());

        ProductStockResponseDto dto = mapper.modelToDto(model);

        Assertions.assertEquals(model.getManufacturingTime(), dto.getManufacturingTime());
        Assertions.assertEquals(model.getDueDate(), dto.getDueDate());
        Assertions.assertEquals(model.getInitialQuantity(), dto.getInitialQuantity());
        Assertions.assertEquals(model.getMinimumTemperature(), dto.getMinimumTemperature());
        Assertions.assertEquals(model.getCurrentQuantity(), dto.getCurrentQuantity());
        Assertions.assertEquals(model.getCurrentTemperature(), dto.getCurrentTemperature());
    }
}
