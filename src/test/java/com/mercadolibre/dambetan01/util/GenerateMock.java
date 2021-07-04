package com.mercadolibre.dambetan01.util;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.dtos.SectorDto;
import com.mercadolibre.dambetan01.model.Category;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.ProductStock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class GenerateMock {
    public static ProductStock createProductStockToPost(){
        ProductStock p1 = new ProductStock();
        Product product = new Product();
        Category category = new Category();
        category.setName("Congelados");
        category.setId(1l);
        product.setCategory(category);

        p1.setManufacturingTime(LocalDateTime.now());
        p1.setManufacturingDate(LocalDate.now());
        p1.setMinimumTemperature(1d);
        p1.setInitialQuantity(1);
        p1.setCurrentTemperature(2d);
        p1.setDueDate(LocalDate.now());
        p1.setProduct(product);

        return p1;
    }

    public static ProductStock createProductStockToPut(){
        ProductStock p1 = new ProductStock();
        Product product = new Product();
        Category category = new Category();
        category.setName("Frios");
        category.setId(2l);
        product.setCategory(category);

        p1.setManufacturingTime(LocalDateTime.now());
        p1.setManufacturingDate(LocalDate.now());
        p1.setMinimumTemperature(1d);
        p1.setCurrentQuantity(9);
        p1.setCurrentTemperature(2d);
        p1.setDueDate(LocalDate.now());
        p1.setProduct(product);

        return p1;
    }

    public static OrderDto createOrderDto(){
        OrderDto dto = new OrderDto();
        SectorDto sectorDto = new SectorDto();
        sectorDto.setSectionCode(1l);
        sectorDto.setWarehouseCode(1l);

        dto.setOrderDate(LocalDate.now());
        dto.setSection(sectorDto);

        ProductStockDto p1 = new ProductStockDto();
        p1.setManufacturingTime(LocalDateTime.now());
        p1.setManufacturingDate(LocalDate.now());
        p1.setMinimumTemperature(1d);
        p1.setInitialQuantity(1);
        p1.setCurrentQuantity(1);
        p1.setCurrentTemperature(2d);
        p1.setDueDate(LocalDate.now());
        p1.setProductId(1l);
        dto.setBatchStock(Arrays.asList(p1));

        return dto;
    }
}
