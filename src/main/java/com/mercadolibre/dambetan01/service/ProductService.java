package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.model.Product;

import java.util.List;

public interface ProductService {
    public Product findById(Long id);

    public List<ProductListDTO> findAllProductsList();
}
