package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductStockPurchaseOrderRepository extends JpaRepository<ProductStockPurchaseOrder, Long> {
    @Query("SELECT SUM(p.bill) FROM ProductStockPurchaseOrder p WHERE p.productStockPurchaseOrderKey.purchaseOrderId = :purchaseOrderId")
    BigDecimal calculateBillByPurchaseOrderId(Long purchaseOrderId);
}
