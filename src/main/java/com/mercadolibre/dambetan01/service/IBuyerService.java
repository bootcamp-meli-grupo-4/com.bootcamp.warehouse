package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.model.user.Buyer;

public interface IBuyerService {
    Buyer findById(Long id);
}
