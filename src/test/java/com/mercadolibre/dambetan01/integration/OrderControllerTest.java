package com.mercadolibre.dambetan01.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dambetan01.dtos.OrderDto;
import com.mercadolibre.dambetan01.dtos.ProductStockDto;
import com.mercadolibre.dambetan01.dtos.SectorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    private static final String PATH = "/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createANewOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderDate(LocalDate.now());
        SectorDto section = new SectorDto();
        section.setSectionCode(2L);
        section.setWarehouseCode(2L);
        orderDto.setSection(section);

        orderDto.setBatchStock(List.of(
                ProductStockDto.builder()
                        .productId(10L)
                        .currentTemperature(300.)
                        .minimumTemperature(4230.)
                        .initialQuantity(1)
                        .manufacturingDate(LocalDate.of(2021,2,1))
                        .manufacturingTime(LocalDateTime.of(2021,2,1, 2,5,41))
                        .dueDate(LocalDate.of(2021,2,1))
                        .build(),
                ProductStockDto.builder()
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


        ResultActions action = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(PATH + "/fresh-products/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(orderDto))
        ).andDo(MockMvcResultHandlers.print());
        action.andExpect(resultMatcher);
    }

    @Test
    public void modifyAOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderDate(LocalDate.now());
        SectorDto section = new SectorDto();
        section.setSectionCode(2L);
        section.setWarehouseCode(2L);
        orderDto.setSection(section);

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


        ResultActions action = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(PATH + "/fresh-products/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(orderDto))
        ).andDo(MockMvcResultHandlers.print());
        action.andExpect(resultMatcher);
    }

}
