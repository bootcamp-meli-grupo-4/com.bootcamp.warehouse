package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Warehouse;
import com.mercadolibre.dambetan01.model.user.Representant;

public interface RepresentantService {
    void checkRelationBetweenRepresentantAndWarehouse(Long id, Warehouse warehouse);
    Representant findById(Long id);
}
