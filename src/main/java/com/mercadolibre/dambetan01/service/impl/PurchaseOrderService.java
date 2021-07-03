package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.purchase.ProductPurchaseOrderDTO;
import com.mercadolibre.dambetan01.mapper.ProductOrderMapper;
import com.mercadolibre.dambetan01.mapper.PurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.user.Buyer;
import com.mercadolibre.dambetan01.model.purchase.ProductStock;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IBuyerService;
import com.mercadolibre.dambetan01.service.IProductStockPurchaseOrderService;
import com.mercadolibre.dambetan01.service.IProductStockService;
import com.mercadolibre.dambetan01.service.IPurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService implements IPurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;

    private final IProductStockService productStockService;

    private final IProductStockPurchaseOrderService productStockPurchaseOrderService;

    private final IBuyerService buyerService;

    @Transactional
    public CreatePurchaseOrderResponseDTO createPurchaseOrder(CreatePurchaseOrderDTO createPurchaseOrderDTO, Long buyerId) {
        PurchaseOrder purchaseOrder = createNewPurchaseOrder(buyerId);

        for (ProductPurchaseOrderDTO productPurchaseOrderDTO : createPurchaseOrderDTO.getProducts()) {
            List<ProductStockPurchaseOrder> productStockPurchaseOrders = productStockService
                    .decrementByProduct(productPurchaseOrderDTO.getQuantity(), productPurchaseOrderDTO.getProductId(), purchaseOrder
            );

            if(productStockPurchaseOrders == null) {
                throw new RuntimeException("Nao ha quantidade suficiente do produto");
            } else if(productStockPurchaseOrders.size() > 0) {
                productStockPurchaseOrderService.saveAll(productStockPurchaseOrders);
            }
        }

        BigDecimal bill = productStockPurchaseOrderService.calculateBillByPurchaseOrderId(purchaseOrder.getId());
        return new CreatePurchaseOrderResponseDTO(bill);
    }

    private PurchaseOrder createNewPurchaseOrder(Long buyerId) {
        StatusPurchaseOrder statusPurchaseOrder = new StatusPurchaseOrder();
        statusPurchaseOrder.setId(1L);
        Buyer buyer = buyerService.findById(buyerId);
        return purchaseOrderRepository.save(PurchaseOrderMapper.buildFrom(buyer, statusPurchaseOrder));
    }
}
