package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;

public interface IPurchaseOrderService {
    CreatePurchaseOrderResponseDTO createPurchaseOrder(CreatePurchaseOrderDTO createPurchaseOrderDTO, Long buyerId);
}
