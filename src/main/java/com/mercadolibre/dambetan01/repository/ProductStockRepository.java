package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.dtos.response.BatchStockDTO;
import com.mercadolibre.dambetan01.model.ProductStock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    List<ProductStock> findByProductIdAndCurrentQuantityGreaterThanEqual(Long productId, Integer currentQuantity);

    List<ProductStock> findByProductId(Long productId);

    @Query("SELECT new com.mercadolibre.dambetan01.dtos.response.BatchStockDTO(ps.id, ps.currentQuantity, ps.dueDate) from ProductStock ps where ps.product.id = :productId")
    List<BatchStockDTO> findByProductId(Long productId, Sort sort);
}
