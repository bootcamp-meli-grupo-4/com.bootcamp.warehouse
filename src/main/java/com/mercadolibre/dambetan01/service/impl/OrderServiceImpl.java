package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.inbound.OrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.PostOrderDto;
import com.mercadolibre.dambetan01.dtos.response.inbound.ProductStockResponseDto;
import com.mercadolibre.dambetan01.mapper.OrderMapper;
import com.mercadolibre.dambetan01.mapper.PostOrderDTOMapper;
import com.mercadolibre.dambetan01.mapper.ProductStockResponseMapper;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.OrderRepository;
import com.mercadolibre.dambetan01.service.OrderService;
import com.mercadolibre.dambetan01.service.RepresentantService;
import com.mercadolibre.dambetan01.service.SectorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ProductStockResponseMapper productStockResponseMapper;
    private SectorService sectorService;
    private ProductStockServiceImpl productStockService;
    private RepresentantService representantService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            ProductStockResponseMapper productStockResponseMapper,
                            SectorService sectorService,
                            ProductStockServiceImpl productStockService,
                            RepresentantService representantService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productStockResponseMapper = productStockResponseMapper;
        this.sectorService = sectorService;
        this.productStockService = productStockService;
        this.representantService = representantService;
    }

    public List<ProductStockResponseDto> crateOrder(PostOrderDto postOrderDto, Long idRepresentant) {
        OrderDto orderDto = PostOrderDTOMapper.buildFrom(postOrderDto);
        Order order = getOrder(orderDto, idRepresentant);

        Order finalOrder = this.orderRepository.save(order);

        List<ProductStock> productStocks = this.productStockService.addOrderOnProductStock(finalOrder);

        this.productStockService.saveAll(productStocks);

        this.sectorService.updateCurrentQuantity(order.getSector());

        return createListProductStockResponseByProductStock(productStocks);
    }

    private Order getOrder(OrderDto orderDto, Long idRepresentant) {
        Order order = orderMapper.dtoToModel(orderDto);

        Long idSection =  orderDto.getSection().getSectionCode();
        Long idWarehouse =  orderDto.getSection().getWarehouseCode();
        Sector sector = this.sectorService.findBySectorAndWarehouse(idSection, idWarehouse);

        Warehouse warehouse = sector.getWarehouse();
        this.representantService.checkRelationBetweenRepresentantAndWarehouse(idRepresentant, warehouse);

        List<ProductStock> productStocks = order.getProductStocks();
        this.productStockService.checkCategoryProductAndSector(productStocks, sector.getCategory());

        Integer quantity = this.productStockService.getQuantityProductsByProductStocks(productStocks);
        this.sectorService.checkSectorSpace(sector, quantity);

        order.setSector(sector);
        return order;
    }

    private List<ProductStockResponseDto> createListProductStockResponseByProductStock(List<ProductStock> productStocks) {
        List<ProductStockResponseDto> productStockResponseDtoList = new ArrayList<>();
        productStocks
                .forEach(p -> productStockResponseDtoList.add(this.productStockResponseMapper.modelToDto(p)));
        return productStockResponseDtoList;
    }


    public List<ProductStockResponseDto> modifyOrder(OrderDto orderDto, Long idRepresentant) {
        Optional<Order> order = orderRepository.findById(orderDto.getOrderNumber());

        if (order.isPresent()){
            orderDto.getBatchStock().forEach(p -> p.setInitialQuantity(0));
        }
        Order orderUpdate = getOrder(orderDto, idRepresentant);

        Order save = orderRepository.save(orderUpdate);

        List<ProductStock> productStocks = this.productStockService.addOrderOnProductStock(orderUpdate);

        this.productStockService.saveAll(productStocks);

        this.sectorService.updateCurrentQuantity(orderUpdate.getSector());

        return createListProductStockResponseByProductStock(save.getProductStocks());
    }

}
