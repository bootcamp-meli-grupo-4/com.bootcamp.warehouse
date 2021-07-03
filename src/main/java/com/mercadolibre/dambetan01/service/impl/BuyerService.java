package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.model.user.Buyer;
import com.mercadolibre.dambetan01.repository.BuyerRepository;
import com.mercadolibre.dambetan01.service.IBuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyerService implements IBuyerService {
    private final BuyerRepository buyerRepository;

    @Override
    public Buyer findById(Long id) {
        Optional<Buyer> buyer = buyerRepository.findById(id);
        if(buyer.isEmpty()) throw new NotFoundException("Buyer Not Found");
        return buyer.get();
    }
}
