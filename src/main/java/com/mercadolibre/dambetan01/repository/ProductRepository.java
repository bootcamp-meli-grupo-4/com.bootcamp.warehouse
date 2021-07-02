package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.dtos.ProductListDTO;
import com.mercadolibre.dambetan01.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT new com.mercadolibre.dambetan01.dtos.ProductListDTO(p.id, p.name, p.category.name," +
            " ps.dueDate)  FROM Product p " +
            "LEFT JOIN ProductStock ps ON (ps.product.id = p.id)" +
            "WHERE ps.dueDate >= :dateValid " +
            "GROUP BY p.id, p.name, p.category, ps.dueDate HAVING SUM(ps.currentQuantity) > 0")
    List<ProductListDTO> findAllProductsList(@Param("dateValid") LocalDate dateValid);
}
