package com.mercadolibre.dambetan01.integration;

import com.mercadolibre.dambetan01.dtos.inbound.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InboundOrderControllerTest extends ControllerTestLoginMvc{
    @BeforeEach
    public void setupToken() throws Exception {
        token = getToken("user_three", "contra123");
    }

    @Test
    public void createANewOrderTest() throws Exception {
        PostOrderDto orderDto = new PostOrderDto();

        orderDto.setOrderDate(LocalDate.now());
        SectorDto section = new SectorDto();
        section.setSectionCode(2L);
        section.setWarehouseCode(2L);
        orderDto.setSection(section);

        orderDto.setBatchStock(List.of(
                PostProductStockDto.builder()
                        .productId(10L)
                        .currentTemperature(300.)
                        .minimumTemperature(4230.)
                        .initialQuantity(1)
                        .manufacturingDate(LocalDate.of(2021,2,1))
                        .manufacturingTime(LocalDateTime.of(2021,2,1, 2,5,41))
                        .dueDate(LocalDate.of(2021,2,1))
                        .build(),
                PostProductStockDto.builder()
                        .productId(9L)
                        .currentTemperature(200.)
                        .minimumTemperature(2.)
                        .initialQuantity(1)
                        .manufacturingDate(LocalDate.of(2021,2,1))
                        .manufacturingTime(LocalDateTime.of(2021,2,1, 2,5,41))
                        .dueDate(LocalDate.of(2021,2,1))
                        .build()
        ));

        ResultMatcher resultMatcher = status().isCreated();

        sendPostRequest("/fresh-products/inboundorder/", orderDto, resultMatcher);
    }

    @Test
    public void modifyAOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderDate(LocalDate.now());
        SectorDto section = new SectorDto();
        section.setSectionCode(2L);
        section.setWarehouseCode(2L);
        orderDto.setSection(section);
        orderDto.setOrderNumber(1L);
        orderDto.setBatchStock(List.of(
                ProductStockDto.builder()
                        .productId(9L)
                        .currentTemperature(300.)
                        .minimumTemperature(1.)
                        .initialQuantity(1)
                        .manufacturingDate(LocalDate.of(2021,2,1))
                        .manufacturingTime(LocalDateTime.of(2021,2,1, 2,5,41))
                        .dueDate(LocalDate.of(2021,2,1))
                        .build()
        ));

        ResultMatcher resultMatcher = status().isCreated();

        sendPutRequest("/fresh-products/inboundorder/"+1, orderDto, resultMatcher);

    }
}
