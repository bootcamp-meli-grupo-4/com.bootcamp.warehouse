package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.ProductStock;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;

import java.util.List;

public interface IProductStockService {
    List<ProductStock> findByProductIdAndCurrentQuantityGreaterThanEqual(Long productId, Integer currentQuantity);

    ProductStock save(ProductStock productStock);

    ProductStock decrementFromProductStock(Integer valueToDecrement, ProductStock productStock);

    List<ProductStockPurchaseOrder> decrementByProduct(Integer valueToDecrement, Long productId, PurchaseOrder purchaseOrder);

    ProductStock incrementStock(Integer valueToIncrement, Long stockId);
}
