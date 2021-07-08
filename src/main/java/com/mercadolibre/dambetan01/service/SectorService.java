package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.Sector;

public interface SectorService {
    public Sector findById(Long id);

    public void checkSectorSpace(Sector sector, int quantity);

    public Sector findBySectorAndWarehouse(Long idSector, Long idWarehouse);

    public Sector updateCurrentQuantity(Sector sector);
}
