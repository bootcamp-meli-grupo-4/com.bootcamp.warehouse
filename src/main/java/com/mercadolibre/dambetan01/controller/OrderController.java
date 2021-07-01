package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api/v1/fresh-products/inboundorder/")
@RestController
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        orderService = orderService;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        List<ProductStockResponseDto> dtos = orderService.crateOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }
}
