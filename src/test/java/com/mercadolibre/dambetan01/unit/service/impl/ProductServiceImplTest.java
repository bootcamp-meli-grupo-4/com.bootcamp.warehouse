package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {
    private ProductRepository repository;
    private ProductServiceImpl service;

    @BeforeEach
    public void setUp(){
        repository = mock(ProductRepository.class);
        service = new ProductServiceImpl(repository);
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
}
