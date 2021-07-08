package com.mercadolibre.dambetan01.unit.mapper;

import com.mercadolibre.dambetan01.dtos.response.purchase.OrderStatusResponseDTO;
import com.mercadolibre.dambetan01.mapper.OrderStatusResponseDTOMapper;
import com.mercadolibre.dambetan01.model.purchase.StatusPurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class OrderStatusResponseDTOMapperTest {
    private OrderStatusResponseDTOMapper mapper;

    @BeforeEach
    public void setUp(){
        this.mapper = new OrderStatusResponseDTOMapper();
    }

    @Test
    public void shouldConvertModelToDtoCorrectly(){
        StatusPurchaseOrder model = new StatusPurchaseOrder();
        model.setName("Finalizado");
        model.setId(3L);
        OrderStatusResponseDTO dto = mapper.modelToDto(model);

        Assertions.assertEquals(model.getName(), dto.getStatusCode());
    }
}
