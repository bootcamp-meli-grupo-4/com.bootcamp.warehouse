package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.response.ProductByWarehouseDTO;
import com.mercadolibre.dambetan01.model.user.EPermission;
import com.mercadolibre.dambetan01.service.IWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/fresh-products/warehouse")
public class WarehouseController {
    private final IWarehouseService warehouseService;

    @GetMapping("/{productId}")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.GET_PRODUCT_BY_WAREHOUSE_PERMISSION  + "')")
    public ProductByWarehouseDTO getProductByWarehouse(@PathVariable Long productId) {
        return warehouseService.findTotalProductByWarehouse(productId);
    }
}
