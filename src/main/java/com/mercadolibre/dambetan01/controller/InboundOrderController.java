package com.mercadolibre.dambetan01.controller;

import com.mercadolibre.dambetan01.dtos.inbound.OrderDto;
import com.mercadolibre.dambetan01.dtos.inbound.PostOrderDto;
import com.mercadolibre.dambetan01.dtos.response.inbound.ProductStockResponseDto;
import com.mercadolibre.dambetan01.model.user.EPermission;
import com.mercadolibre.dambetan01.service.OrderService;
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
public class InboundOrderController {
    private final OrderService orderService;


    @PostMapping("/inboundorder")
    @PreAuthorize("hasAuthority('" + EPermission.Constants.REGISTER_STOCK_PERMISSION  + "')")
    public ResponseEntity createOrder(@Valid @RequestBody PostOrderDto orderDto, Authentication authentication){
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
}
