package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Product;
import com.mercadolibre.dambetan01.repository.ProductRepository;
import com.mercadolibre.dambetan01.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    final private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found product with id "+id));
    }

    @Override
    public List<ProductListDTO> findAllProductsList() {

        LocalDate today = LocalDate.now();
        LocalDate threeWeeksAgo = today.minusWeeks(3);

        List<ProductListDTO> productList = repository.findAllProductsList(threeWeeksAgo);

        if (productList.size() == 0) throw new NotFoundException("Not found products");

       return productList;
    }


}
