package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.response.ProductByWarehouseDTO;
import com.mercadolibre.dambetan01.dtos.response.TotalProductByWareHouseDTO;
import com.mercadolibre.dambetan01.repository.WarehouseRepository;
import com.mercadolibre.dambetan01.service.IWarehouseService;
import com.mercadolibre.dambetan01.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouseService {
    private final WarehouseRepository warehouseRepository;

    private final ProductService productService;

    @Override
    public ProductByWarehouseDTO findTotalProductByWarehouse(Long productId) {
        productService.findById(productId);
        ProductByWarehouseDTO productByWarehouseDTO = new ProductByWarehouseDTO();
        List<TotalProductByWareHouseDTO> totalProductByWareHouseDTOList = warehouseRepository.findTotalProductsByWarehouse(productId);
        productByWarehouseDTO.setProductId(productId);
        productByWarehouseDTO.setWarehouses(totalProductByWareHouseDTOList);
        return productByWarehouseDTO;
    }
}
