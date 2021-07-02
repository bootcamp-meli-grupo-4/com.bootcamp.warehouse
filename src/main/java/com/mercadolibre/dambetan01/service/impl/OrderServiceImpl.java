package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
import com.mercadolibre.dambetan01.exceptions.InvalidRepresentant;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.mapper.OrderMapper;
import com.mercadolibre.dambetan01.mapper.ProductStockResponseMapper;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.repository.AccountRepository;
import com.mercadolibre.dambetan01.repository.OrderRepository;
import com.mercadolibre.dambetan01.repository.RepresentantRepository;
import com.mercadolibre.dambetan01.service.OrderService;
import com.mercadolibre.dambetan01.service.SectorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private ProductStockResponseMapper productStockResponseMapper;
    private SectorService sectorService;
    private ProductStockServiceImpl productStockService;
    private RepresentantRepository representantRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper,
                            ProductStockResponseMapper productStockResponseMapper,
                            SectorService sectorService, ProductStockServiceImpl productStockService,
                            RepresentantRepository representantRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productStockResponseMapper = productStockResponseMapper;
        this.sectorService = sectorService;
        this.productStockService = productStockService;
        this.representantRepository = representantRepository;
    }

    public List<ProductStockResponseDto> crateOrder(OrderDto orderDto, Long idRepresentant) {
        Order order = checkOrderValues(orderDto, idRepresentant);

        Order finalOrder = orderRepository.save(order);

        List<ProductStock> productStocks = getProductStockByOrder(finalOrder);
        productStockService.saveAll(productStocks);

        return createListProductStockResponseByProductStock(productStocks);
    }

    private Order checkOrderValues(OrderDto orderDto, Long idRepresentant) {
        Order order = orderMapper.dtoToModel(orderDto);

        Sector sector = sectorService.findById(orderDto.getSection().getSectionCode());
        checkRelationBetweenWarehouseAndSector(orderDto.getSection().getWarehouseCode(), sector);

        Warehouse warehouse = sector.getWarehouse();
        checkRelationBetweenRepresentantAndWarehouse(idRepresentant, warehouse);

        List<ProductStock> productStocks = order.getProductStocks();
        this.checkCategoryProductAndSector(productStocks, sector);

        Integer quantity = getQuantityProducts(productStocks);
        this.sectorService.checkSectorSpace(sector, quantity);

        order.setSector(sector);
        return order;
    }

    private void checkRelationBetweenRepresentantAndWarehouse(Long idRepresentant, Warehouse warehouse) {
        Optional<Representant> representant = representantRepository.findById(idRepresentant);
        if(representant.isEmpty() || !warehouse.getRepresentant().getId().equals(representant.get().getId())){
            throw new InvalidRepresentant("This representant doesn't put product on this warehouse");
        }
    }

    private void checkRelationBetweenWarehouseAndSector(Long idWarehouse, Sector sector) {
        if (!idWarehouse.equals(sector.getWarehouse().getId())) {
            throw new NotFoundException("Not found relationship between section[" + sector.getId() + "]" +
                    " and warehouse[" + idWarehouse + "]");
        }
    }

    private Integer getQuantityProducts(List<ProductStock> productStocks) {
        AtomicInteger q = new AtomicInteger();
        Optional<Integer> quantity = productStocks.stream()
                .map(ProductStock::getInitialQuantity)
                .filter(Objects::nonNull)
                .reduce(Integer::sum);

        if (quantity.isEmpty()) {
            quantity = productStocks.stream()
                    .map(ProductStock::getCurrentQuantity)
                    .filter(Objects::nonNull)
                    .reduce(Integer::sum);
        }
        return quantity.get();
    }

    private void checkCategoryProductAndSector(List<ProductStock> productStocks, Sector sector) {
        productStocks.stream()
                .map(ProductStock::getProduct)
                .filter(product -> !product.getCategory().getName().equals(sector.getCategory().getName()))
                .findFirst()
                .ifPresent(product -> {
                    throw new IllegalCategoryProductSector("Product " + product.getName() + " has category diff of Sector");
                });
    }

    private List<ProductStock> getProductStockByOrder(Order order) {
        List<ProductStock> productStocks = order.getProductStocks();
        productStocks.forEach(productStock -> productStock.setCurrentQuantity(productStock.getInitialQuantity()));
        productStocks.forEach(p -> p.setOrder(order));
        return productStocks;
    }

    private List<ProductStockResponseDto> createListProductStockResponseByProductStock(List<ProductStock> productStocks) {
        List<ProductStockResponseDto> productStockResponseDtoList = new ArrayList<>();
        productStocks
                .forEach(p -> productStockResponseDtoList.add(productStockResponseMapper.modelToDto(p)));
        return productStockResponseDtoList;
    }
}
