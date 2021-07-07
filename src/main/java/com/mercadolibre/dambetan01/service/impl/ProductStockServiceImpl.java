package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.IllegalCategoryProductSector;
import com.mercadolibre.dambetan01.model.Category;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.repository.ProductStockRepository;
import com.mercadolibre.dambetan01.service.ProductStockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductStockServiceImpl implements ProductStockService {
    private final ProductStockRepository repository;

    public ProductStockServiceImpl(ProductStockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductStock> saveAll(List<ProductStock> productStock) {
        return repository.saveAll(productStock);
    }

    @Override
    public void checkCategoryProductAndSector(List<ProductStock> productStocks, Category category) {
        productStocks.stream()
                .map(ProductStock::getProduct)
                .filter(product -> !product.getCategory().getName().equalsIgnoreCase(category.getName()))
                .findFirst()
                .ifPresent(product -> {
                    throw new IllegalCategoryProductSector("Product " + product.getName() + " has category diff of Sector");
                });
    }

    public Integer getQuantityProductsByProductStocks(List<ProductStock> productStocks) {
        Optional<Integer> quantity = productStocks.stream()
                .map(ProductStock::getInitialQuantity)
                .filter(Objects::nonNull)
                .reduce(Integer::sum);

        if (quantity.isEmpty() || quantity.get() == 0 ) {
            quantity = productStocks.stream()
                    .map(ProductStock::getCurrentQuantity)
                    .filter(Objects::nonNull)
                    .reduce(Integer::sum);
        }
        return quantity.orElse(0);
    }

    public List<ProductStock> addOrderOnProductStock(Order order) {
        List<ProductStock> productStocks = order.getProductStocks();
        productStocks.forEach(p -> p.setOrder(order));
        return productStocks;
    }
}
