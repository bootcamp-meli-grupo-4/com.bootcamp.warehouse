package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.inbound.OrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.PostOrderDto;
import com.mercadolibre.dambetan01.dtos.response.inbound.ProductStockResponseDto;

import java.util.List;

public interface OrderService {
    List<ProductStockResponseDto> crateOrder(PostOrderDto orderDto, Long idRepresentant);

    List<ProductStockResponseDto> modifyOrder(OrderDto orderDto, Long idRepresentant);
}
