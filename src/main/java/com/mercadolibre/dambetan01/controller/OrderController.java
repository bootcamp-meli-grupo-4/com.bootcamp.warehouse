package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.response.ProductStockResponseDto;
import com.mercadolibre.dambetan01.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/fresh-products")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/inboundorder/")
    public ResponseEntity createOrder(@Valid @RequestBody OrderDto orderDto, @RequestParam Long idRepresentant){
        List<ProductStockResponseDto> dtos = orderService.crateOrder(orderDto, idRepresentant);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
    }

}
