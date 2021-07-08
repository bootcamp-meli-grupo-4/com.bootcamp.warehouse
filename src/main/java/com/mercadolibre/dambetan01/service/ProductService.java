package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductStockSearchDTO;
import com.mercadolibre.dambetan01.model.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    List<ProductListDTO> findAllProductsList();

    List<ProductListDTO> findAllProductsListByCategory(String category);

    ProductStockSearchDTO findAllProductsByIdAndSort(Long idProduct, String order);
}
