package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NoSectorSpace;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.Sector;
import com.mercadolibre.dambetan01.repository.SectorRepository;
import com.mercadolibre.dambetan01.service.SectorService;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl implements SectorService {

    private SectorRepository repository;

    public SectorServiceImpl(SectorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Sector findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found sector with code "+id));
    }

    @Override
    public void checkSectorSpace(Sector sector, int quantity) {
        Long quantityProduct = sector.getCurrentQuantity() + quantity;
        if (sector.getQuantityMax() < quantityProduct) {
           throw new NoSectorSpace("The sector doesn't have space, limit max: "+sector.getQuantityMax());
        }
    }

    public Sector findBySectorAndWarehouse(Long idSector, Long idWarehouse) {
        return repository.findByIdAndWarehouse(idSector, idWarehouse)
                .orElseThrow(()->
                        new NotFoundException("Not found relationship between section[" + idSector + "]" +
                " and warehouse[" + idWarehouse + "]"));
    }

    private Long sumCurrentQuantity(Long sectorId) {
        return repository.sumCurrentQuantity(sectorId);
    }

    private Sector save(Sector sector){
        return repository.save(sector);
    }

    @Override
    public Sector updateCurrentQuantity(Sector sector) {
        Long currentQuantity = this.sumCurrentQuantity(sector.getId());
        sector.setCurrentQuantity(currentQuantity);
        return this.save(sector);
    }
}
