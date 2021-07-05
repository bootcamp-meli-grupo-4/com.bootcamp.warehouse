package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.response.purchase.GetPurchaseOrderResponseDTO;

public interface IPurchaseOrderService {
    CreatePurchaseOrderResponseDTO createPurchaseOrder(CreatePurchaseOrderDTO createPurchaseOrderDTO, Long buyerId);

    GetPurchaseOrderResponseDTO getOrderById(Long orderId, Long buyerId);

}
