package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.SectorDto;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.model.Sector;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements Mapper<Order, OrderDto>{
    private ProductStockMapper productStockMapper;

    public OrderMapper(ProductStockMapper productStockMapper) {
        this.productStockMapper = productStockMapper;
    }

    @Override
    public Order dtoToModel(OrderDto dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDate());
        List<ProductStock> productStocks = new ArrayList<>();
        dto.getProductStocks().forEach(p-> productStocks.add(productStockMapper.dtoToModel(p)));
        order.setProductStocks(productStocks);
        return order;
    }

    @Override
    public OrderDto modelToDto(Order model) {
        return null;
    }
}
