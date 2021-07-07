package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.inbound.OrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.ProductStockDto;
import com.mercadolibre.dambetan01.mapper.OrderMapper;
import com.mercadolibre.dambetan01.mapper.ProductStockMapper;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderMapperTest {
    private ProductStockMapper productStockMapper;
    private OrderMapper mapper;

    @BeforeEach
    public void setUp(){
        productStockMapper = mock(ProductStockMapper.class);
        this.mapper = new OrderMapper(productStockMapper);
    }

    @Test
    public void shouldConvertDtoToModelCorrectly(){
        OrderDto dto = new OrderDto();
        dto.setOrderDate(LocalDate.now());
        dto.setOrderNumber(1L);
        ProductStockDto productStockDto = new ProductStockDto();
        productStockDto.setProductId(1L);
        List<ProductStockDto> batchStock = Arrays.asList(productStockDto);
        dto.setBatchStock(batchStock);

        when(productStockMapper.dtoToModel(productStockDto)).thenReturn(new ProductStock());

        Order order = mapper.dtoToModel(dto);

        Assertions.assertEquals(dto.getOrderNumber(), order.getId());
        Assertions.assertEquals(dto.getOrderDate(), order.getOrderDate());
        Assertions.assertNotNull(order.getProductStocks());
    }
}
