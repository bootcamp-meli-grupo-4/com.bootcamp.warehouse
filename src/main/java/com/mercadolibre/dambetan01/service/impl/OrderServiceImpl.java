package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.mapper.OrderMapper;
import com.mercadolibre.dambetan01.mapper.ProductStockResponseMapper;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.model.Sector;
import com.mercadolibre.dambetan01.repository.OrderRepository;
import com.mercadolibre.dambetan01.service.OrderService;
import com.mercadolibre.dambetan01.service.SectorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ProductStockResponseMapper productStockResponseMapper;
    private SectorService sectorService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ProductStockResponseMapper productStockResponseMapper, SectorService sectorService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productStockResponseMapper = productStockResponseMapper;
        this.sectorService = sectorService;
    }

    public List<ProductStockResponseDto> crateOrder(OrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);
        Sector sector = sectorService.findById(orderDto.getSection().getSectionCode());
        Long idWarehouse = orderDto.getSection().getWarehouseCode();
        if(!idWarehouse.equals(sector.getWarehouse().getId())){
            throw new NotFoundException("Not found relationship between section["+sector.getId()+"]" +
                    " and warehouse["+idWarehouse+"]");
        }
        order.setSector(sector);

        orderRepository.save(order);

        return createListProductStockResponseByProductStock(order.getProductStocks());
    }

    private List<ProductStockResponseDto> createListProductStockResponseByProductStock(List<ProductStock> productStocks) {
        List<ProductStockResponseDto> productStockResponseDtoList = new ArrayList<>();
        productStocks
                .forEach(p-> productStockResponseDtoList.add(productStockResponseMapper.modelToDto(p)));
        return productStockResponseDtoList;
    }
}
