package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.model.purchase.ProductStock;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;

import java.math.BigDecimal;

public class ProductStockPurchaseOrderMapper {
    public static ProductStockPurchaseOrder buildFrom(ProductStock productStocks, Integer quantity, PurchaseOrder purchaseOrder) {
        ProductStockPurchaseOrder productStockPurchaseOrder = new ProductStockPurchaseOrder();
        productStockPurchaseOrder.setProduct(productStocks.getProduct());
        productStockPurchaseOrder.setQuantity(quantity);
        productStockPurchaseOrder.setProductStock(productStocks);
        productStockPurchaseOrder.setPurchaseOrder(purchaseOrder);
        BigDecimal bill = BigDecimal.valueOf(quantity).multiply(productStocks.getProduct().getPrice());
        productStockPurchaseOrder.setBill(bill);
        return productStockPurchaseOrder;
    }
}
