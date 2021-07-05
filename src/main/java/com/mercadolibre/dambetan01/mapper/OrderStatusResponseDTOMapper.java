package com.mercadolibre.dambetan01.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.OrderStatusResponseDTO;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusResponseDTOMapper implements MapperModelToDto<StatusPurchaseOrder,OrderStatusResponseDTO>{

    @Override
    public OrderStatusResponseDTO modelToDto(StatusPurchaseOrder model) {
        OrderStatusResponseDTO dto = new OrderStatusResponseDTO();
        dto.setStatusCode(model.getName());
        return dto;
    }
}
