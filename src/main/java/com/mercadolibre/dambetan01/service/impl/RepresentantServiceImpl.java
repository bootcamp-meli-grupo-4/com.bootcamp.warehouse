package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.InvalidRepresentant;
import com.mercadolibre.dambetan01.model.Warehouse;
import com.mercadolibre.dambetan01.model.user.Representant;
import com.mercadolibre.dambetan01.repository.RepresentantRepository;
import com.mercadolibre.dambetan01.service.RepresentantService;
import org.springframework.stereotype.Service;

@Service
public class RepresentantServiceImpl implements RepresentantService {
    private RepresentantRepository repository;

    public RepresentantServiceImpl(RepresentantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void checkRelationBetweenRepresentantAndWarehouse(Long id, Warehouse warehouse) {
        Representant representant = this.findById(id);
        if (!warehouse.getRepresentant().getId().equals(representant.getId())) {
            throw new InvalidRepresentant("This representant doesn't put product on this warehouse");
        }
    }

    @Override
    public Representant findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new InvalidRepresentant("Not found representant with id "+id));
    }
}
