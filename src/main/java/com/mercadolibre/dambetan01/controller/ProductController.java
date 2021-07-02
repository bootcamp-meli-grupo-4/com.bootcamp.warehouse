package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/fresh-products/")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductListDTO> findAllProductsList(){
        return  productService.findAllProductsList();
    }

    @GetMapping("/list?{querytype}")
    public List<ProductListDTO> findAllProductsListCategory(@PathVariable String querytype){
        return  productService.findAllProductsList();
    }


}
