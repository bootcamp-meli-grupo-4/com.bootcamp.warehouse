package com.mercadolibre.dambetan01.service.impl;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.purchase.EditPurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.EditPurchaseOrderResponse;
import com.mercadolibre.dambetan01.dtos.purchase.ProductPurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductUnavailableResponseDTO;
import com.mercadolibre.dambetan01.exceptions.BadRequestException;
import com.mercadolibre.dambetan01.exceptions.NotFoundException;
import com.mercadolibre.dambetan01.exceptions.ProductUnavailableException;
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
import java.util.Optional;

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

        List<ProductUnavailableResponseDTO> productsUnavailable = bookProducts(createPurchaseOrderDTO, purchaseOrder);

        if(productsUnavailable.size() > 0) throw new ProductUnavailableException("product unavailable", HttpStatus.BAD_REQUEST, productsUnavailable);

        BigDecimal bill = productStockPurchaseOrderService.calculateBillByPurchaseOrderId(purchaseOrder.getId());
        return new CreatePurchaseOrderResponseDTO(bill);
    }

    @Transactional
    public EditPurchaseOrderResponse editPurchaseOrder(EditPurchaseOrderDTO editPurchaseOrderDTO, Long purchaseOrderId, Long buyerId) {
        Optional<PurchaseOrder> purchaseOrderOp = purchaseOrderRepository.findById(purchaseOrderId);

        if(purchaseOrderOp.isEmpty()) throw new NotFoundException("not found purchase order with id " + purchaseOrderId);

        PurchaseOrder purchaseOrder = purchaseOrderOp.get();

        if(!purchaseOrder.getBuyer().getId().equals(buyerId)) throw new BadRequestException("not allowed to change the order");

        if(undoPurchaseOrder(purchaseOrder)) {
            CreatePurchaseOrderDTO createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
            createPurchaseOrderDTO.setProducts(new ArrayList<>());
            editPurchaseOrderDTO.getProducts().forEach(product -> createPurchaseOrderDTO.getProducts().add(product));
            List<ProductUnavailableResponseDTO> productsUnavailable = bookProducts(createPurchaseOrderDTO, purchaseOrder);
            if(productsUnavailable.size() > 0) throw new ProductUnavailableException("product unavailable", HttpStatus.BAD_REQUEST, productsUnavailable);
        }

        BigDecimal bill = productStockPurchaseOrderService.calculateBillByPurchaseOrderId(purchaseOrder.getId());
        return new EditPurchaseOrderResponse(bill);
    }

    private List<ProductUnavailableResponseDTO> bookProducts(CreatePurchaseOrderDTO createPurchaseOrderDTO, PurchaseOrder purchaseOrder) {
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

        return productsUnavailable;
    }

    private boolean undoPurchaseOrder(PurchaseOrder purchaseOrder) {
        for (ProductStockPurchaseOrder productStockPurchaseOrder: purchaseOrder.getProducts()) {
            productStockService.incrementStock(productStockPurchaseOrder.getQuantity(), productStockPurchaseOrder.getProductStock().getId());
        }
        productStockPurchaseOrderService.deleteAllByPurchaseOrderId(purchaseOrder.getId());
        return true;
    }

    private PurchaseOrder createNewPurchaseOrder(Long buyerId) {
        StatusPurchaseOrder statusPurchaseOrder = new StatusPurchaseOrder();
        statusPurchaseOrder.setId(1L);
        Buyer buyer = buyerService.findById(buyerId);
        return purchaseOrderRepository.save(PurchaseOrderMapper.buildFrom(buyer, statusPurchaseOrder));
    }
}
