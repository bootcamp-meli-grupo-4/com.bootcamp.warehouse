package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.mapper.ProductStockMapper;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductStockMapperTest {
    private ProductStockMapper mapper;
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        productService = mock(ProductService.class);
        this.mapper = new ProductStockMapper(productService);
    }

    @Test
    public void shouldConvertDtoToModelCorrectly(){
        ProductStockDto dto = new ProductStockDto();
        dto.setProductId(1L);
        dto.setBatchNumber(1L);
        dto.setDueDate(LocalDate.now());
        dto.setInitialQuantity(2);
        when(productService.findById(dto.getProductId())).thenReturn(new Product());
        ProductStock model = mapper.dtoToModel(dto);
        Assertions.assertEquals(dto.getDueDate(), model.getDueDate());
        Assertions.assertEquals(dto.getInitialQuantity(), model.getInitialQuantity());
        Assertions.assertEquals(dto.getBatchNumber(), model.getId());
        Assertions.assertNotNull(model.getProduct());
    }


    @Test
    public void shouldConvertModelToDtoCorrectly(){
        ProductStock model = new ProductStock();
        model.setCurrentQuantity(3);
        model.setId(1L);
        model.setManufacturingTime(LocalDateTime.now());
        ProductStockDto dto = mapper.modelToDto(model);
        Assertions.assertEquals(model.getCurrentQuantity(), dto.getCurrentQuantity());
        Assertions.assertEquals(model.getManufacturingTime(), dto.getManufacturingTime());
    }

}
