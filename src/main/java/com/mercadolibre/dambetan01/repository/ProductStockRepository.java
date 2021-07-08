package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.dtos.inbound.SectorDto;
import com.mercadolibre.dambetan01.dtos.response.BatchStockDTO;
import com.mercadolibre.dambetan01.dtos.ProductDueDateDTO;
import com.mercadolibre.dambetan01.model.ProductStock;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    List<ProductStock> findByProductIdAndCurrentQuantityGreaterThanEqual(Long productId, Integer currentQuantity);

    List<ProductStock> findByProductId(Long productId);

    @Query("SELECT new com.mercadolibre.dambetan01.dtos.response.BatchStockDTO(ps.id, ps.currentQuantity, ps.dueDate) from ProductStock ps where ps.product.id = :productId")
    List<BatchStockDTO> findByProductId(Long productId, Sort sort);

    @Query("SELECT DISTINCT new com.mercadolibre.dambetan01.dtos.inbound.SectorDto(ps.order.sector.id, ps.order.sector.warehouse.id) FROM ProductStock ps WHERE ps.product.id = :productId")
    SectorDto findByProductBySector(Long productId);

    @Query("SELECT new com.mercadolibre.dambetan01.dtos.ProductDueDateDTO(orr.id, ps.product.id, sc.category.id, ps.dueDate, " +
            " ps.currentQuantity ) FROM ProductStock ps " +
            "JOIN Order orr ON (ps.order.id = orr.id) " +
            "JOIN Sector sc ON (orr.sector.id = sc.id) " +
            "WHERE ps.dueDate <= :dateFuture " +
            "AND orr.sector.id = :idSector " +
            "ORDER BY ps.dueDate DESC ")
    List<ProductDueDateDTO> findAllProductStockDueDate(@Param("dateFuture")LocalDate dateFuture, @Param("idSector") Long idSector);

    @Query("SELECT new com.mercadolibre.dambetan01.dtos.ProductDueDateDTO(orr.id, ps.product.id, sc.category.id, ps.dueDate, " +
            "ps.currentQuantity ) FROM ProductStock ps " +
            "JOIN Order orr ON (ps.order.id = orr.id) " +
            "JOIN Sector sc ON (orr.sector.id = sc.id) " +
            "WHERE ps.dueDate <= :dateFuture " +
            "AND sc.warehouse.representant.id = :idRepresentant " +
            "AND ps.product.category.name = :nameCategory " +
            "ORDER BY ps.dueDate DESC ")
    List<ProductDueDateDTO> findAllProductStockDueDateCategory(@Param("dateFuture")LocalDate dateFuture,
                                                               @Param("nameCategory") String nameCategory,
                                                               @Param("idRepresentant") Long idRepresentant);
}
