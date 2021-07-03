package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.purchase.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    List<ProductStock> findByProductIdAndCurrentQuantityGreaterThanEqual(Long productId, Integer currentQuantity);

    List<ProductStock> findByProductId(Long productId);
}
