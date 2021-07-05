package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.exceptions.NoSectorSpace;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Sector;
import com.mercadolibre.dambetan01.repository.SectorRepository;
import com.mercadolibre.dambetan01.service.impl.SectorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class SectorServiceImplTest {

    private SectorRepository sectorRepository;
    private SectorServiceImpl sectorService;

    @BeforeEach
    public void setUp(){
        sectorRepository = mock(SectorRepository.class);
        sectorService = new SectorServiceImpl(sectorRepository);
    }

    @Test
    public void shouldFindByIdCorrectly() {
        Long id = 1l;
        when(sectorRepository.findById(id)).thenReturn(Optional.of(new Sector()));
        sectorService.findById(id);
        verify(sectorRepository, times(1)).findById(id);
    }

    @Test
    public void shouldShowExceptionWhenFindById() {
        Long id = 1l;
        when(sectorRepository.findById(id)).thenReturn(Optional.empty());


        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            sectorService.findById(id);
        });

        String expectedMessage = "Not Found Exception. Not found sector with code 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(sectorRepository, times(1)).findById(id);
    }

    @ParameterizedTest
    @ValueSource(longs = {10, 14, 7})
    public void shouldHaveSpaceAvailableWhenCheckSectorSpace(long qtdMax) {
        Sector sector = new Sector();
        sector.setQuantityMax(qtdMax);
        sector.setCurrentQuantity(3l);
        sectorService.checkSectorSpace(sector, 4);
    }

    @ParameterizedTest
    @ValueSource(longs = {10, 14, 7})
    public void shouldShowExceptionWhenCheckSectorSpace(long qtdMax) {
        int qtdProduct = 9;
        Sector sector = new Sector();
        sector.setQuantityMax(qtdMax);
        sector.setCurrentQuantity(6l);

        NoSectorSpace exception = assertThrows(NoSectorSpace.class, () -> {
            sectorService.checkSectorSpace(sector, qtdProduct);
        });

        String expectedMessage = "The sector doesn't have space, limit max: "+qtdMax;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldFindBySectorAndWarehouseCorrectly() {
        Long idSector = 1l;
        Long idWarehouse = 1l;

        when(sectorRepository.findByIdAndWarehouse(idSector, idWarehouse)).thenReturn(Optional.of(new Sector()));
        sectorService.findBySectorAndWarehouse(idSector, idWarehouse);

        verify(sectorRepository, times(1)).findByIdAndWarehouse(idSector, idWarehouse);
    }

    @Test
    public void shouldShowExceptionWhenFindBySectorAndWarehouse() {
        Long idSector = 1l;
        Long idWarehouse = 2l;

        when(sectorRepository.findByIdAndWarehouse(idSector, idWarehouse)).thenReturn(Optional.empty());
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            sectorService.findBySectorAndWarehouse(idSector, idWarehouse);
        });


        String expectedMessage = "Not Found Exception. Not found relationship between section[" + idSector + "]" +
                " and warehouse[" + idWarehouse + "]";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
        verify(sectorRepository, times(1)).findByIdAndWarehouse(idSector, idWarehouse);
    }
}