package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.ProductNotFound;
import com.mercadolibre.dambetan01.exceptions.SectorNotFound;
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
                .orElseThrow(() -> new SectorNotFound("Not found sector with code "+id));
    }
}
