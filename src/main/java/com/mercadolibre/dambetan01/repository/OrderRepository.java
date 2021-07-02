package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
