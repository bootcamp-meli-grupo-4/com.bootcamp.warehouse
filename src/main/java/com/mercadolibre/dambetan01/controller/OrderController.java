package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.EditPurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductByWarehouseDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.model.user.EPermission;
import com.mercadolibre.dambetan01.service.IPurchaseOrderService;
import com.mercadolibre.dambetan01.service.OrderService;
import com.mercadolibre.dambetan01.service.IWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/fresh-products")
public class OrderController {

    private final OrderService orderService;

    private final IPurchaseOrderService purchaseOrderService;

    private final IWarehouseService warehouseService;

    @PostMapping("/inboundorder")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.REGISTER_STOCK_PERMISSION  + "')")
    public ResponseEntity createOrder(@Valid @RequestBody OrderDto orderDto,  Authentication authentication){
        Long representantId = Long.parseLong((String)authentication.getPrincipal());
        List<ProductStockResponseDto> dtos = orderService.crateOrder(orderDto, representantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping("/inboundorder/{orderNumber}")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.REGISTER_STOCK_PERMISSION  + "')")
    public ResponseEntity<List<ProductStockResponseDto>> modifyOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable Long orderNumber, Authentication authentication){
        orderDto.setOrderNumber(orderNumber);
        Long representantId = Long.parseLong((String)authentication.getPrincipal());
        List<ProductStockResponseDto> productStockResponseDtos = orderService.modifyOrder(orderDto, representantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(productStockResponseDtos);
    }

    @PostMapping("/orders")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.BUY_PRODUCT_PERMISSION + "')")
    public ResponseEntity<?> createPurchaseOrder(@RequestBody @Valid CreatePurchaseOrderDTO createPurchaseOrderDTO, Authentication authentication) {
        Long buyerId = Long.parseLong((String)authentication.getPrincipal());
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(createPurchaseOrderDTO, buyerId), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{purchaseOrderId}")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.EDIT_PURCHASE_ORDER_PERMISSION + "')")
    public ResponseEntity<?> editPurchaseOrder(@PathVariable Long purchaseOrderId,
                                               @RequestBody @Valid EditPurchaseOrderDTO editPurchaseOrderDTO,
                                               Authentication authentication
    ) {
        Long buyerId = Long.parseLong((String)authentication.getPrincipal());
        return new ResponseEntity<>(purchaseOrderService.editPurchaseOrder(editPurchaseOrderDTO, purchaseOrderId, buyerId), HttpStatus.OK);
    }

    @GetMapping("orders/{orderId}")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.BUY_PRODUCT_PERMISSION  + "')")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId, Authentication authentication){
        Long buyerId = Long.parseLong((String)authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(purchaseOrderService.getOrderById(orderId, buyerId));
    }

    @GetMapping("/warehouse/{productId}")
    public ProductByWarehouseDTO getProductByWarehouse(@PathVariable Long productId) {
        return warehouseService.findTotalProductByWarehouse(productId);
    }
}
