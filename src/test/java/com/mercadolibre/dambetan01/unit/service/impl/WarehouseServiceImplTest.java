package com.mercadolibre.dambetan01.unit.service.impl;

import com.mercadolibre.dambetan01.dtos.response.ProductByWarehouseDTO;
import com.mercadolibre.dambetan01.dtos.response.TotalProductByWareHouseDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.repository.WarehouseRepository;
import com.mercadolibre.dambetan01.service.ProductService;
import com.mercadolibre.dambetan01.service.impl.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class WarehouseServiceImplTest {
    WarehouseRepository warehouseRepository = Mockito.mock(WarehouseRepository.class);

    ProductService productService = Mockito.mock(ProductService.class);

    WarehouseService warehouseService;

    @BeforeEach
    void setUp() {
        warehouseService = new WarehouseService(warehouseRepository, productService);

        when(productService.findById(111L)).thenThrow(new NotFoundException("Not found product with id " + 111));
        when(productService.findById(1L)).thenReturn(new Product());
        when(warehouseRepository.findTotalProductsByWarehouse(1L)).thenReturn(
                List.of(
                        new TotalProductByWareHouseDTO(1L, 1L),
                        new TotalProductByWareHouseDTO(2L, 5L)
                )
        );
    }

    @Test
    public void do_get_product_by_warehouse_return_200() throws Exception {
        ProductByWarehouseDTO productByWarehouseDTO = warehouseService.findTotalProductByWarehouse(1L);
        assertNotNull(productByWarehouseDTO);
        assertNotNull(productByWarehouseDTO.getWarehouses());
        assertNotNull(productByWarehouseDTO.getWarehouses());
        assertEquals(2, productByWarehouseDTO.getWarehouses().size());
    }

    @Test
    public void do_get_product_that_does_not_exist_by_warehouse_return_404() throws Exception {
        assertThrows(NotFoundException.class, () -> warehouseService.findTotalProductByWarehouse(111L));
    }

}
