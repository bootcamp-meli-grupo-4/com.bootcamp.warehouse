package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.service.IPurchaseOrderService;
import com.mercadolibre.dambetan01.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(path = "/api/v1/fresh-products")
public class OrderController {

    private final OrderService orderService;

    private final IPurchaseOrderService purchaseOrderService;

    @PostMapping("/inboundorder")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        List<ProductStockResponseDto> dtos = orderService.crateOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createPurchaseOrder(@RequestBody @Valid CreatePurchaseOrderDTO createPurchaseOrderDTO) {
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(createPurchaseOrderDTO, 5L), HttpStatus.CREATED);
    }
}
