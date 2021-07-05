package com.mercadolibre.dambetan01.service;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.purchase.EditPurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.EditPurchaseOrderResponse;

public interface IPurchaseOrderService {
    CreatePurchaseOrderResponseDTO createPurchaseOrder(CreatePurchaseOrderDTO createPurchaseOrderDTO, Long buyerId);

    EditPurchaseOrderResponse editPurchaseOrder(EditPurchaseOrderDTO editPurchaseOrderDTO, Long purchaseOrderId, Long buyerId);
}
