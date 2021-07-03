package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.purchase.ProductStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderMapper implements MapperDtoToModel<Order, OrderDto>{
    private ProductStockMapper productStockMapper;

    public OrderMapper(ProductStockMapper productStockMapper) {
        this.productStockMapper = productStockMapper;
    }

    @Override
    public Order dtoToModel(OrderDto dto) {
        Order order = new Order();
        if(dto.getOrderNumber() != null) {
            order.setId(dto.getOrderNumber());
        }
        order.setOrderDate(dto.getOrderDate());
        List<ProductStock> productStocks = new ArrayList<>();
        dto.getBatchStock().forEach(p-> productStocks.add(productStockMapper.dtoToModel(p)));
        order.setProductStocks(productStocks);
        return order;
    }



}
