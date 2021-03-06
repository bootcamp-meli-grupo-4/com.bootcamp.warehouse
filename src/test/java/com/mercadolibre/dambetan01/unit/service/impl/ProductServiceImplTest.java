package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductRepository repository;
    private ProductServiceImpl service;
    private static List<ProductListDTO> productListDTO;


    @BeforeEach
    public void setUp(){
        repository = mock(ProductRepository.class);
        final ProductStockRepository productStockRepository = mock(ProductStockRepository.class);
        service = new ProductServiceImpl(repository, productStockRepository);
        productListDTO = List
                .of(new ProductListDTO(1L,"Uva", "fresco", LocalDate.of(2021,12,1)));
    }

    @Test
    public void shouldFindByIdCorrectly() {
        Long id = 1l;
        when(repository.findById(id)).thenReturn(Optional.of(new Product()));
        service.findById(id);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldShowExceptionWhenFindById() {
        Long id = 1l;
        when(repository.findById(id)).thenReturn(Optional.empty());


        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findById(id);
        });

        String expectedMessage = "Not Found Exception. Not found product with id 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllProductsListCorrectly(){

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        when(repository.findAllProductsList(threeWeeksAgo)).thenReturn(productListDTO);
        service.findAllProductsList();
        verify(repository, times(1)).findAllProductsList(threeWeeksAgo);

    }

    @Test
    public void shouldShowExceptionWhenFindAllProductList(){

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        when(repository.findAllProductsList(threeWeeksAgo)).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findAllProductsList();
        });

        String expectedMessage = "Not Found Exception. Not found products";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findAllProductsList(threeWeeksAgo);
    }

    @Test
    public void shouldFindAllProductsListByCategoryCorrectly(){

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        when(repository.findAllProductsList(threeWeeksAgo)).thenReturn(productListDTO);
        service.findAllProductsListByCategory("FR");
        verify(repository, times(1)).findAllProductsList(threeWeeksAgo);

    }

    @Test
    public void shouldShowExceptionWhenFindAllProductListByCategory(){

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        when(repository.findAllProductsList(threeWeeksAgo)).thenReturn(productListDTO);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            service.findAllProductsListByCategory("RF");
        });

        String expectedMessage = "Not Found Exception. Not found products";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findAllProductsList(threeWeeksAgo);
    }
}
