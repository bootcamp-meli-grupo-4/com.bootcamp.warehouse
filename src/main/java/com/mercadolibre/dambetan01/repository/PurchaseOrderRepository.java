package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}
