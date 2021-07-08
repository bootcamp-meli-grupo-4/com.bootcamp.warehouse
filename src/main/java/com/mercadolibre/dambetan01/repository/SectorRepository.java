package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    @Query(value = "SELECT * FROM sector WHERE id = ? AND warehouse_id = ?;", nativeQuery = true)
    Optional<Sector> findByIdAndWarehouse(@Param("idSector") Long idSector, @Param("idWarehouse") Long idWarehouse);

    @Query(value = "SELECT SUM(ps.current_quantity) FROM sector s\n" +
            "JOIN orders ord ON ord.sector_id = s.id\n" +
            "JOIN product_stock ps ON ps.order_id = ord.id\n" +
            "WHERE s.id = ?;", nativeQuery = true)
    Long sumCurrentQuantity(Long sectorId);
}
