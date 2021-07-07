package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.ProductDueDateDTO;
import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.*;
import com.mercadolibre.dambetan01.model.user.Representant;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.SectorService;
import com.mercadolibre.dambetan01.service.impl.ProductStockServiceImpl;
import com.mercadolibre.dambetan01.service.impl.RepresentantServiceImpl;
import com.mercadolibre.dambetan01.service.impl.SectorServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.representer.Represent;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.dambetan01.util.GenerateMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProductStockServiceImplTest {
    private static ProductStockRepository repository;
    private static ProductStockServiceImpl service;
    private static SectorServiceImpl sectorService;
    private static RepresentantServiceImpl representantService;
    private static List<ProductStock> productStockList;
    private static List<ProductDueDateDTO> productDueDateDTO;

    @BeforeAll
    public static void setUp(){
        repository = mock(ProductStockRepository.class);
        sectorService = mock(SectorServiceImpl.class);
        representantService = mock(RepresentantServiceImpl.class);
        service = new ProductStockServiceImpl(repository, sectorService, representantService);
        productStockList = Arrays.asList(createProductStockToPost());
        productDueDateDTO = List
                .of(new ProductDueDateDTO(1L, 1L, 1L, LocalDate.of(2021, 6,30), 20) );
    }

    @Test
    public void shouldSaveAllCorrectly() {
        when(repository.saveAll(productStockList)).thenReturn(productStockList);
        service.saveAll(productStockList);
        verify(repository, times(1)).saveAll(productStockList);
    }

    @Test
    public void shouldCheckCategoryProductAndSectorCorrectly(){
        Category category = new Category();
        category.setName("Congelados");
        category.setId(1l);
        service.checkCategoryProductAndSector(productStockList, category);
    }

    @Test
    public void shouldShowExceptionWhenCheckCategoryProductAndSector(){
        Category category = new Category();
        category.setName("Frios");
        category.setId(2l);

        IllegalCategoryProductSector exception = assertThrows(IllegalCategoryProductSector.class, () -> {
           service.checkCategoryProductAndSector(productStockList, category);

        });

        String expectedMessage = "Product " + productStockList.get(0).getProduct().getName() + " has category diff of Sector";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldGetQuantityProductsByProductStocksByInitialQuantity(){
        int qtd = service.getQuantityProductsByProductStocks(productStockList);
        assertEquals(1, qtd);
    }

    @Test
    public void shouldGetQuantityProductsByProductStocksByCurrentQuantity(){
        List<ProductStock> productStocks = Arrays.asList(createProductStockToPut());
        int qtd = service.getQuantityProductsByProductStocks(productStocks);
        assertEquals(9, qtd);
    }

    @Test
    public void shouldAddOrderOnProductStockCorrectly(){
        Order order = new Order();
        order.setProductStocks(productStockList);
        order.setId(1l);
        List<ProductStock> result = service.addOrderOnProductStock(order);
        assertEquals(order, result.get(0).getOrder());
    }

    @Test
    public void shouldFindAllProductStockDueDateBySectorCorrectly(){

        LocalDate today = LocalDate.now();
        LocalDate dateDaysFuture = today.plusDays(90);
        Warehouse warehouse = mock(Warehouse.class);
        Category category = mock(Category.class);
        Representant representant = mock(Representant.class);
        Sector sector = new Sector(1L, warehouse, List.of(), category, 1L, 1L);

        when(sectorService.findById(1L)).thenReturn(sector);

        when(repository.findAllProductStockDueDate(dateDaysFuture, sector.getId())).thenReturn(productDueDateDTO);
        service.findAllProductStockDueDateBySector(90, sector.getId(), representant.getId());
        verify(repository, times(1)).findAllProductStockDueDate(dateDaysFuture, sector.getId());
    }

    @Test
    public void shouldFindAllProductStockDueDateBySectorException(){

        LocalDate today = LocalDate.now();
        LocalDate dateDaysFuture = today.plusDays(90);
        Warehouse warehouse = mock(Warehouse.class);
        Category category = mock(Category.class);
        Representant representant = mock(Representant.class);
        Sector sector = new Sector(1L, warehouse, List.of(), category, 1L, 1L);

        when(sectorService.findById(1L)).thenReturn(sector);

        when(repository.findAllProductStockDueDate(dateDaysFuture, sector.getId())).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findAllProductStockDueDateBySector(90, sector.getId(), representant.getId());
        });

        String expectedMessage = "Not Found Exception. Not found products in sector";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

        verify(repository, times(1)).findAllProductStockDueDate(dateDaysFuture, sector.getId());
    }
}
