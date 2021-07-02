package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
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
    private ProductStockServiceImpl productStockService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            ProductStockResponseMapper productStockResponseMapper,
                            SectorService sectorService, ProductStockServiceImpl productStockService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productStockResponseMapper = productStockResponseMapper;
        this.sectorService = sectorService;
        this.productStockService = productStockService;
    }

    public List<ProductStockResponseDto> crateOrder(OrderDto orderDto) {
        Order order = orderMapper.dtoToModel(orderDto);

        Sector sector = sectorService.findById(orderDto.getSection().getSectionCode());
        Long idWarehouse = orderDto.getSection().getWarehouseCode();

        if(!idWarehouse.equals(sector.getWarehouse().getId())){
            throw new NotFoundException("Not found relationship between section["+sector.getId()+"]" +
                    " and warehouse["+idWarehouse+"]");
        }

        this.checkCategoryProductAndSector(order.getProductStocks(), sector);

        order.setSector(sector);

        Order finalOrder = orderRepository.save(order);

        List<ProductStock> productStocks = getProductStockByOrder(finalOrder);
        productStockService.saveAll(productStocks);

       return createListProductStockResponseByProductStock(order.getProductStocks());
    }

    private void checkCategoryProductAndSector(List<ProductStock> productStocks, Sector sector){
        productStocks.stream()
                .map(ProductStock::getProduct)
                .filter(product -> !product.getCategory().getName().equals(sector.getCategory().getName()))
                .findFirst()
                .ifPresent(product -> {
                    throw new IllegalCategoryProductSector("Product "+product.getName()+" has category diff of Sector");
                });
    }

    private List<ProductStock> getProductStockByOrder(Order order){
        List<ProductStock> productStocks = order.getProductStocks();
        productStocks.forEach(productStock -> productStock.setCurrentQuantity(productStock.getInitialQuantity()));
        productStocks.forEach(p -> p.setOrder(order));
        return productStocks;
    }

    private List<ProductStockResponseDto> createListProductStockResponseByProductStock(List<ProductStock> productStocks) {
        List<ProductStockResponseDto> productStockResponseDtoList = new ArrayList<>();
        productStocks
                .forEach(p-> productStockResponseDtoList.add(productStockResponseMapper.modelToDto(p)));
        return productStockResponseDtoList;
    }
}