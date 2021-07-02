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
}
