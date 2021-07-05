package com.mercadolibre.dambetan01.unit.service;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderResponseDTO;
import com.mercadolibre.dambetan01.dtos.purchase.ProductPurchaseOrderDTO;
import com.mercadolibre.dambetan01.exceptions.ProductUnavailableException;
import com.mercadolibre.dambetan01.mapper.GetPurchaseOrderResponseDTOMapper;
import com.mercadolibre.dambetan01.mapper.PurchaseOrderMapper;
import com.mercadolibre.dambetan01.model.purchase.ProductStockPurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.PurchaseOrder;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import com.mercadolibre.dambetan01.model.user.Buyer;
import com.mercadolibre.dambetan01.repository.PurchaseOrderRepository;
import com.mercadolibre.dambetan01.service.IBuyerService;
import com.mercadolibre.dambetan01.service.IProductStockPurchaseOrderService;
import com.mercadolibre.dambetan01.service.IProductStockService;
import com.mercadolibre.dambetan01.service.impl.PurchaseOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PurchaseOrderServiceImplTest {

    IProductStockPurchaseOrderService productStockPurchaseOrderService = Mockito.mock(IProductStockPurchaseOrderService.class);

    IProductStockService productStockService = Mockito.mock(IProductStockService.class);

    IBuyerService buyerService = Mockito.mock(IBuyerService.class);

    PurchaseOrderRepository purchaseOrderRepository = Mockito.mock(PurchaseOrderRepository.class);

    GetPurchaseOrderResponseDTOMapper getPurchaseOrderResponseDTOMapper = Mockito.mock(GetPurchaseOrderResponseDTOMapper.class);

    PurchaseOrderService purchaseOrderService;

    CreatePurchaseOrderDTO createPurchaseOrderDTO;

    @BeforeEach
    void setUp() {
        purchaseOrderService = new PurchaseOrderService(purchaseOrderRepository,
                productStockService,
                productStockPurchaseOrderService,
                buyerService,
                getPurchaseOrderResponseDTOMapper);
        when(buyerService.findById(5L)).thenReturn(
                createBuyer(5L)
        );

        when(buyerService.findById(1L)).thenReturn(createBuyer(5L));
        PurchaseOrder purchaseOrder = createPurchaseOrder(createBuyer(5L));
        purchaseOrder.setId(1L);
        when(purchaseOrderRepository.save(any())).thenReturn(purchaseOrder);

        when(productStockService.decrementByProduct(5, 1L, purchaseOrder)).thenReturn(List.of(new ProductStockPurchaseOrder()));
        when(productStockService.decrementByProduct(11, 1L, purchaseOrder)).thenReturn(null);
        when(productStockService.decrementByProduct(2, 2L, purchaseOrder)).thenReturn(List.of(new ProductStockPurchaseOrder()));

        when(productStockPurchaseOrderService.calculateBillByPurchaseOrderId(1L)).thenReturn(BigDecimal.valueOf(150.00));
    }

    @Test
    public void do_purchase_order_return_bill() throws Exception {
        createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
        createPurchaseOrderDTO.setDate(LocalDate.now());
        createPurchaseOrderDTO.setProducts(List.of(
                new ProductPurchaseOrderDTO(1L, 5),
                new ProductPurchaseOrderDTO(2L, 2)
        ));

        CreatePurchaseOrderResponseDTO createPurchaseOrderResponseDTO = purchaseOrderService.createPurchaseOrder(createPurchaseOrderDTO, 5L);
        assertEquals(BigDecimal.valueOf(150.00), createPurchaseOrderResponseDTO.getTotalPrice());
    }

    @Test
    public void do_purchase_order_with_unavailable_product_throw_ProductUnavailableException() {
        createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
        createPurchaseOrderDTO.setDate(LocalDate.now());
        createPurchaseOrderDTO.setProducts(List.of(
                new ProductPurchaseOrderDTO(1L, 11),
                new ProductPurchaseOrderDTO(2L, 2)
        ));

        assertThrows(ProductUnavailableException.class, () -> purchaseOrderService.createPurchaseOrder(createPurchaseOrderDTO, 5L));
    }

    private Buyer createBuyer(Long id) {
        Buyer buyer = new Buyer();
        buyer.setPurchaseOrder(null);
        buyer.setId(id);
        buyer.setUsername("user_five");
        buyer.setPassword("contra123");
        return buyer;
    }

    private PurchaseOrder createPurchaseOrder(Buyer buyer) {
        StatusPurchaseOrder statusPurchaseOrder = new StatusPurchaseOrder();
        statusPurchaseOrder.setId(1L);
        return PurchaseOrderMapper.buildFrom(buyer, statusPurchaseOrder);
    }
}
