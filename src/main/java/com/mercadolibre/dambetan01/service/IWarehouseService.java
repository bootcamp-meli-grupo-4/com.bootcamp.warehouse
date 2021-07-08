package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.response.ProductByWarehouseDTO;

public interface IWarehouseService {
    ProductByWarehouseDTO findTotalProductByWarehouse(Long productId);
}
