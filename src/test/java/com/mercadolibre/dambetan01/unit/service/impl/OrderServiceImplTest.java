package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.inbound.PostOrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.PostProductStockDto;
import com.mercadolibre.dambetan01.dtos.inbound.SectorDto;
import com.mercadolibre.dambetan01.dtos.response.inbound.ProductStockResponseDto;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.dambetan01.unit.service.impl.ProductStockServiceImplTest.createProductStockToPost;
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
        PostOrderDto dto = createPostOrderDto();
        Order order = mock(Order.class);
        Sector sector = mock(Sector.class);
        Category category = mock(Category.class);
        Warehouse warehouse = mock(Warehouse.class);
        ProductStock productStock = createProductStockToPost();

        ProductStockResponseDto productStockResponseDto = new ProductStockResponseDto();
        productStockResponseDto.setCurrentQuantity(1);

        when(orderMapper.dtoToModel(any())).thenReturn(order);
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


    public PostOrderDto createPostOrderDto(){
        PostOrderDto dto = new PostOrderDto();
        SectorDto sectorDto = new SectorDto();
        sectorDto.setSectionCode(1l);
        sectorDto.setWarehouseCode(1l);

        dto.setOrderDate(LocalDate.now());
        dto.setSection(sectorDto);

        PostProductStockDto p1 = new PostProductStockDto();
        p1.setManufacturingTime(LocalDateTime.now());
        p1.setManufacturingDate(LocalDate.now());
        p1.setMinimumTemperature(1d);
        p1.setInitialQuantity(1);
        p1.setCurrentTemperature(2d);
        p1.setDueDate(LocalDate.now());
        p1.setProductId(1l);
        dto.setBatchStock(Arrays.asList(p1));

        return dto;
    }
}