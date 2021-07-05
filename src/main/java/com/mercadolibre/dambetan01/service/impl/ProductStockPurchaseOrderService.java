package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.repository.ProductStockPurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IProductStockPurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStockPurchaseOrderService implements IProductStockPurchaseOrderService {
    private final ProductStockPurchaseOrderRepository productStockPurchaseOrderRepository;

    @Override
    public ProductStockPurchaseOrder save(ProductStockPurchaseOrder productOrder) {
        return productStockPurchaseOrderRepository.save(productOrder);
    }

    @Override
    public List<ProductStockPurchaseOrder> saveAll(Iterable<ProductStockPurchaseOrder> productStockPurchaseOrders) {
        return productStockPurchaseOrderRepository.saveAll(productStockPurchaseOrders);
    }

    @Override
    public BigDecimal calculateBillByPurchaseOrderId(Long purchaseOrderId) {
        return productStockPurchaseOrderRepository.calculateBillByPurchaseOrderId(purchaseOrderId);
    }
}
