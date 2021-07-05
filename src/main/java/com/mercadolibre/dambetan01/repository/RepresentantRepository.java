package com.mercadolibre.dambetan01.repository;

import com.mercadolibre.dambetan01.model.user.Representant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentantRepository extends JpaRepository<Representant, Long> {

}
