package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStockPurchaseOrderService {
    ProductStockPurchaseOrder save(ProductStockPurchaseOrder productOrder);

    List<ProductStockPurchaseOrder> saveAll(Iterable<ProductStockPurchaseOrder> productStockPurchaseOrders);

    BigDecimal calculateBillByPurchaseOrderId(Long purchaseOrderId);
}
