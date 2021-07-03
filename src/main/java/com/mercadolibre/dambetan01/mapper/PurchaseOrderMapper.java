package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import com.mercadolibre.dambetan01.model.user.Buyer;

import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrderMapper {
    public static PurchaseOrder buildFrom(Buyer buyer, StatusPurchaseOrder statusPurchaseOrder) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setBuyer(buyer);
        purchaseOrder.setStatusPurchaseOrder(statusPurchaseOrder);
        purchaseOrder.setProducts(new ArrayList<>());
        purchaseOrder.setDate(LocalDate.now());
        return purchaseOrder;
    }
}
