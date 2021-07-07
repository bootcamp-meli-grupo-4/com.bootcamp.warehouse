package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.model.user.EPermission;
import com.mercadolibre.dambetan01.service.ProductStockService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/fresh-products")

public class ProductStockController {

    private ProductStockService productStockService;

    public ProductStockController(ProductStockService productStockService){
        this.productStockService = productStockService;
    }


    @GetMapping("/due-date/")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.REGISTER_STOCK_PERMISSION  + "')")
    public ResponseEntity<?> findAllProductStockDueDateBySector(@RequestParam(defaultValue = "0") Integer quantityDay, @RequestParam Long idSector, Authentication authentication){

        Long idRepresentant = Long.parseLong((String)authentication.getPrincipal());
        return ResponseEntity.ok().body(productStockService.findAllProductStockDueDateBySector(quantityDay,idSector, idRepresentant));

    }

}
