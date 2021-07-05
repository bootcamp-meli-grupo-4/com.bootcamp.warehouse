package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query(value = "SELECT * FROM purchase_order WHERE id = ? AND buyer_id = ?;", nativeQuery = true)
    Optional<PurchaseOrder> findByIdAndBuyerId(Long id, Long buyerId);
}
