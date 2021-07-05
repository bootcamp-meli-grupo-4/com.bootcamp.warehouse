package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/fresh-products/inboundorder/")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        List<ProductStockResponseDto> dtos = orderService.crateOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

    @PutMapping("/{orderNumber}")
    public ResponseEntity<List<ProductStockResponseDto>> modifyOrder(@RequestBody OrderDto orderDto, @PathVariable Long orderNumber){
        orderDto.setOrderNumber(orderNumber);
        List<ProductStockResponseDto> productStockResponseDtos = orderService.modifyOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productStockResponseDtos);
    }
}
