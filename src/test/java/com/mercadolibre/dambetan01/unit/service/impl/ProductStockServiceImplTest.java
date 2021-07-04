package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
import com.mercadolibre.dambetan01.model.Category;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.impl.ProductStockServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.mercadolibre.dambetan01.util.GenerateMock.createProductStockToPost;
import static com.mercadolibre.dambetan01.util.GenerateMock.createProductStockToPut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ProductStockServiceImplTest {
    private static ProductStockRepository repository;
    private static ProductStockServiceImpl service;
    private static List<ProductStock> productStockList;

    @BeforeAll
    public static void setUp(){
        repository = mock(ProductStockRepository.class);
        service = new ProductStockServiceImpl(repository);
        productStockList = Arrays.asList(createProductStockToPost());
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
}
