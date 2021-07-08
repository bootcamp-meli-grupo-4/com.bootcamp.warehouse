package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductStockSearchDTO;
import com.mercadolibre.dambetan01.model.user.EPermission;
import com.mercadolibre.dambetan01.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/fresh-products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.LIST_ALL_PRODUCT_PERMISSION  + "')")
    public List<ProductListDTO> findAllProductsList(){
        return  productService.findAllProductsList();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.LIST_ALL_PRODUCT_PER_CATEGORY_PERMISSION  + "')")
    public List<ProductListDTO> findAllProductsListCategory(@RequestParam String category){
        return  productService.findAllProductsListByCategory(category);
    }
}
