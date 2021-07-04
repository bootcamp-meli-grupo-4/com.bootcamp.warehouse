package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Category;
import com.mercadolibre.dambetan01.model.Order;
import com.mercadolibre.dambetan01.model.ProductStock;

import java.util.List;

public interface ProductStockService {
    public List<ProductStock> saveAll(List<ProductStock> productStock);

    public void checkCategoryProductAndSector(List<ProductStock> productStocks, Category category);

    public Integer getQuantityProductsByProductStocks(List<ProductStock> productStocks);

    List<ProductStock> addOrderOnProductStock(Order order);
}
