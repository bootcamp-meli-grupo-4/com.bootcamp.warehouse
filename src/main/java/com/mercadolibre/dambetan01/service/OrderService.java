package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;

import java.util.List;

public interface OrderService {
    List<ProductStockResponseDto> crateOrder(OrderDto orderDto);
}