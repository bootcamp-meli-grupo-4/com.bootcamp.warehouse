package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.model.purchase.ProductStock;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;

public class ProductOrderMapper {

    public static ProductStockPurchaseOrder buildFrom(PurchaseOrder purchaseOrder, ProductStock productStock, Integer quantity) {
        ProductStockPurchaseOrder productOrder = new ProductStockPurchaseOrder();

        productOrder.setProduct(productStock.getProduct());
        productOrder.setPurchaseOrder(purchaseOrder);
        productOrder.setProductStock(productStock);
        productOrder.setQuantity(quantity);

        return productOrder;
    }
}
