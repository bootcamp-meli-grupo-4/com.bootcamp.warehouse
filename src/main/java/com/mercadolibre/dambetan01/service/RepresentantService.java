package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Representant;
import com.mercadolibre.dambetan01.model.Warehouse;

public interface RepresentantService {
    void checkRelationBetweenRepresentantAndWarehouse(Long id, Warehouse warehouse);
    Representant findById(Long id);
}
