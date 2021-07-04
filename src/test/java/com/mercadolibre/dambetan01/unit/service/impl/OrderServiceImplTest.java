package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.mapper.OrderMapper;
import com.mercadolibre.dambetan01.mapper.ProductStockResponseMapper;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.repository.OrderRepository;
import com.mercadolibre.dambetan01.service.OrderService;
import com.mercadolibre.dambetan01.service.RepresentantService;
import com.mercadolibre.dambetan01.service.SectorService;
import com.mercadolibre.dambetan01.service.impl.OrderServiceImpl;
import com.mercadolibre.dambetan01.service.impl.ProductStockServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.dambetan01.util.GenerateMock.createOrderDto;
import static com.mercadolibre.dambetan01.util.GenerateMock.createProductStockToPost;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceImplTest {

    private static OrderRepository orderRepository;
    private static OrderMapper orderMapper;
    private static ProductStockResponseMapper productStockResponseMapper;
    private static SectorService sectorService;
    private static ProductStockServiceImpl productStockService;
    private static RepresentantService representantService;
    private static OrderService orderService;

    @BeforeAll
    public static void setUp(){
        orderRepository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        productStockResponseMapper = mock(ProductStockResponseMapper.class);
        sectorService = mock(SectorService.class);
        productStockService = mock(ProductStockServiceImpl.class);
        representantService = mock(RepresentantService.class);
        orderService = new OrderServiceImpl(orderRepository, orderMapper, productStockResponseMapper,
                sectorService, productStockService, representantService);

    }

    @Test
    public void shouldCreateOrderCorrectly(){
        Long idRepresentant = 1l;
        OrderDto dto = createOrderDto();
        Order order = mock(Order.class);
        Sector sector = mock(Sector.class);
        Category category = mock(Category.class);
        Warehouse warehouse = mock(Warehouse.class);
        ProductStock productStock = createProductStockToPost();

        ProductStockResponseDto productStockResponseDto = new ProductStockResponseDto();
        productStockResponseDto.setCurrentQuantity(1);

        when(orderMapper.dtoToModel(dto)).thenReturn(order);
        when(sectorService.findBySectorAndWarehouse(dto.getSection().getSectionCode(), dto.getSection().getWarehouseCode()))
                .thenReturn(sector);
        when(sector.getWarehouse()).thenReturn(warehouse);
        when(sector.getCategory()).thenReturn(category);
        List<ProductStock> productStocksList = Arrays.asList(productStock);
        when(this.productStockService.getQuantityProductsByProductStocks(productStocksList)).thenReturn(1);
        when(order.getProductStocks()).thenReturn(productStocksList);
        when(orderRepository.save(order)).thenReturn(order);
        when(productStockResponseMapper.modelToDto(productStock)).thenReturn(productStockResponseDto);
        when(productStockService.addOrderOnProductStock(order)).thenReturn(productStocksList);
        when(productStockService.saveAll(productStocksList)).thenReturn(productStocksList);

        List<ProductStockResponseDto> responseDto = orderService.crateOrder(dto, idRepresentant);

        Assertions.assertNotNull(responseDto);
        Assertions.assertFalse(responseDto.isEmpty());
        Assertions.assertEquals(1, responseDto.size());
        Assertions.assertEquals(1, responseDto.get(0).getCurrentQuantity());
        verify(representantService, times(1)).checkRelationBetweenRepresentantAndWarehouse(idRepresentant, warehouse);
        verify(productStockService, times(1)).checkCategoryProductAndSector(productStocksList, category);
        verify(sectorService, times(1)).checkSectorSpace(sector, 1);
        verify(productStockService, times(1)).saveAll(productStocksList);
    }






}