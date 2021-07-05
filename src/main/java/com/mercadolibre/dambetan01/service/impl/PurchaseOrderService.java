package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.purchase.ProductPurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductUnavailableResponseDTO;
import com.mercadolibre.dambetan01.exceptions.ProductUnavailableException;
import com.mercadolibre.dambetan01.dtos.response.purchase.GetPurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.exceptions.ProductUnavailableException;
import com.mercadolibre.dambetan01.mapper.GetPurchaseOrderResponseDTOMapper;
import com.mercadolibre.dambetan01.mapper.PurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.user.Buyer;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IBuyerService;
import com.mercadolibre.dambetan01.service.IProductStockPurchaseOrderService;
import com.mercadolibre.dambetan01.service.IProductStockService;
import com.mercadolibre.dambetan01.service.IPurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService implements IPurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;

    private final IProductStockService productStockService;

    private final IProductStockPurchaseOrderService productStockPurchaseOrderService;

    private final IBuyerService buyerService;

    private final GetPurchaseOrderResponseDTOMapper purchaseOrderResponseDTOMapper;

    @Transactional
    public CreatePurchaseOrderResponseDTO createPurchaseOrder(CreatePurchaseOrderDTO createPurchaseOrderDTO, Long buyerId) {
        PurchaseOrder purchaseOrder = createNewPurchaseOrder(buyerId);

        List<ProductUnavailableResponseDTO> productsUnavailable = new ArrayList<>();

        for (ProductPurchaseOrderDTO productPurchaseOrderDTO : createPurchaseOrderDTO.getProducts()) {
            List<ProductStockPurchaseOrder> productStockPurchaseOrders = productStockService
                    .decrementByProduct(productPurchaseOrderDTO.getQuantity(), productPurchaseOrderDTO.getProductId(), purchaseOrder
            );

            if(productStockPurchaseOrders == null) {
                productsUnavailable.add(new ProductUnavailableResponseDTO(productPurchaseOrderDTO.getProductId()));
            } else if(productStockPurchaseOrders.size() > 0) {
                productStockPurchaseOrderService.saveAll(productStockPurchaseOrders);
            }
        }

        if(productsUnavailable.size() > 0) throw new ProductUnavailableException("product unavailable", HttpStatus.BAD_REQUEST, productsUnavailable);

        BigDecimal bill = productStockPurchaseOrderService.calculateBillByPurchaseOrderId(purchaseOrder.getId());
        return new CreatePurchaseOrderResponseDTO(bill);
    }

    private PurchaseOrder createNewPurchaseOrder(Long buyerId) {
        StatusPurchaseOrder statusPurchaseOrder = new StatusPurchaseOrder();
        statusPurchaseOrder.setId(1L);
        Buyer buyer = buyerService.findById(buyerId);
        return purchaseOrderRepository.save(PurchaseOrderMapper.buildFrom(buyer, statusPurchaseOrder));
    }

    public GetPurchaseOrderResponseDTO getOrderById(Long orderId, Long buyerId){
        PurchaseOrder purchaseOrder = this.findByIdAndBuyerId(orderId, buyerId);
        BigDecimal bill = productStockPurchaseOrderService.calculateBillByPurchaseOrderId(purchaseOrder.getId());
        GetPurchaseOrderResponseDTO dto = purchaseOrderResponseDTOMapper.modelToDto(purchaseOrder);
        dto.setTotalPrice(bill);
        return dto;
    }

    private PurchaseOrder findByIdAndBuyerId(Long orderId, Long buyerId){
        return purchaseOrderRepository
                .findByIdAndBuyerId(orderId, buyerId)
                .orElseThrow(() -> new NotFoundException("User ["+buyerId+"] purchase order not found"));
    }
}
