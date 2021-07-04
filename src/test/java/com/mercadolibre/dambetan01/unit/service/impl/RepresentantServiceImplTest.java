package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.exceptions.InvalidRepresentant;
import com.mercadolibre.dambetan01.model.Representant;
import com.mercadolibre.dambetan01.model.Warehouse;
import com.mercadolibre.dambetan01.repository.RepresentantRepository;
import com.mercadolibre.dambetan01.service.impl.RepresentantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RepresentantServiceImplTest {
    
    private RepresentantRepository repository;
    private RepresentantServiceImpl service;

    @BeforeEach
    public void setUp(){
        repository = mock(RepresentantRepository.class);
        service = new RepresentantServiceImpl(repository);
    }

    @Test
    public void shouldFindByIdCorrectly() {
        Long id = 1l;
        when(repository.findById(id)).thenReturn(Optional.of(new Representant()));
        service.findById(id);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldShowExceptionWhenFindById() {
        Long id = 1l;
        when(repository.findById(id)).thenReturn(Optional.empty());


        InvalidRepresentant exception = assertThrows(InvalidRepresentant.class, () -> {
            service.findById(id);
        });

        String expectedMessage = "Not found representant with id 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldCheckRelationBetweenRepresentantAndWarehouseCorrectly(){
        Long id = 1l;
        Representant representant = new Representant();
        representant.setId(id);
        Warehouse warehouse = new Warehouse();
        warehouse.setRepresentant(representant);
        when(repository.findById(id)).thenReturn(Optional.of(representant));
        service.checkRelationBetweenRepresentantAndWarehouse(id, warehouse);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldShowExceptionWhenCheckRelationBetweenRepresentantAndWarehouseNotFoundRepresentant(){
        Long id = 1l;
        Representant representant = new Representant();
        representant.setId(id);
        Warehouse warehouse = new Warehouse();
        warehouse.setRepresentant(representant);
        when(repository.findById(id)).thenReturn(Optional.empty());

        InvalidRepresentant exception = assertThrows(InvalidRepresentant.class, () -> {
            service.checkRelationBetweenRepresentantAndWarehouse(id, warehouse);
        });

        String expectedMessage = "Not found representant with id 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findById(id);

    }

    @Test
    public void shouldShowExceptionWhenCheckRelationBetweenRepresentantAndWarehouseInvalid(){
        Long id = 1l;
        Representant representant = new Representant();
        Representant representant2 = new Representant();
        representant.setId(id);
        representant2.setId(2l);
        Warehouse warehouse = new Warehouse();
        warehouse.setRepresentant(representant);
        when(repository.findById(id)).thenReturn(Optional.of(representant2));

        InvalidRepresentant exception = assertThrows(InvalidRepresentant.class, () -> {
            service.checkRelationBetweenRepresentantAndWarehouse(id, warehouse);
        });

        String expectedMessage = "This representant doesn't put product on this warehouse";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(repository, times(1)).findById(id);

    }

}
