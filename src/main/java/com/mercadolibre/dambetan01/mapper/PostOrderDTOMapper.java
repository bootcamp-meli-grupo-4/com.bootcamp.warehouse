package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.inbound.OrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.PostOrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.ProductStockDto;

import java.util.ArrayList;
import java.util.List;

public class PostOrderDTOMapper {
    public static OrderDto buildFrom(PostOrderDto postOrderDto){
        OrderDto dto = new OrderDto();
        dto.setSection(postOrderDto.getSection());
        dto.setOrderDate(postOrderDto.getOrderDate());
        List<ProductStockDto> productStockDtoList = new ArrayList<>();
        postOrderDto.getBatchStock().forEach(s -> productStockDtoList.add(PostProductStockDTOMapper.buildFrom(s)));
        dto.setBatchStock(productStockDtoList);
        return dto;
    }
}
