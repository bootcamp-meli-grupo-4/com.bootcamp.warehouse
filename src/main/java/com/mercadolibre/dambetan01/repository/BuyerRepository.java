package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.user.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
