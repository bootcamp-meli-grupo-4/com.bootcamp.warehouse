package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.dtos.response.TotalProductByWareHouseDTO;
import com.mercadolibre.dambetan01.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(
            "SELECT new com.mercadolibre.dambetan01.dtos.response.TotalProductByWareHouseDTO(wh.id, SUM(ps.currentQuantity)) \n" +
                    "FROM Product p \n" +
                    "JOIN ProductStock ps ON ps.product.id = p.id AND p.id = :productId \n" +
                    "JOIN Order ord ON ord.id = ps.order.id\n" +
                    "JOIN Sector sct ON ord.sector.id = sct.id\n" +
                    "JOIN Warehouse wh ON wh.id = sct.warehouse.id GROUP BY wh.id"
    )
    List<TotalProductByWareHouseDTO> findTotalProductsByWarehouse(@Param("productId") Long productId);
}
