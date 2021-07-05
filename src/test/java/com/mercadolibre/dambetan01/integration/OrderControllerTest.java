package com.mercadolibre.dambetan01.integration;

import com.mercadolibre.dambetan01.dtos.purchase.CreatePurchaseOrderDTO;
import com.mercadolibre.dambetan01.dtos.purchase.ProductPurchaseOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends ControllerTestLoginMvc {
    @BeforeEach
    public void setupToken() throws Exception {
        token = getToken("user_five", "contra123");
    }

    @Test
    public void do_purchase_order_return_201() throws Exception {
        CreatePurchaseOrderDTO createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
        createPurchaseOrderDTO.setDate(LocalDate.now());
        createPurchaseOrderDTO.setProducts(List.of(
                new ProductPurchaseOrderDTO(1L, 6),
                new ProductPurchaseOrderDTO(2L, 2)
        ));
        ResultMatcher resultMatcher = status().isCreated();
        sendPostRequest("/fresh-products/orders", createPurchaseOrderDTO, resultMatcher);
    }

    @Test
    public void do_purchase_order_with_unavailable_product_return_400() throws Exception {
        CreatePurchaseOrderDTO createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
        createPurchaseOrderDTO.setDate(LocalDate.now());
        createPurchaseOrderDTO.setProducts(List.of(
                new ProductPurchaseOrderDTO(1L, 11),
                new ProductPurchaseOrderDTO(2L, 2)
        ));
        ResultMatcher resultMatcher = status().isBadRequest();
        sendPostRequest("/fresh-products/orders", createPurchaseOrderDTO, resultMatcher);
    }

    @Test
    public void do_purchase_with_empty_product_list_return_400() throws Exception {
        CreatePurchaseOrderDTO createPurchaseOrderDTO = new CreatePurchaseOrderDTO();
        createPurchaseOrderDTO.setDate(LocalDate.now());
        ResultMatcher resultMatcher = status().isBadRequest();
        sendPostRequest("/fresh-products/orders", createPurchaseOrderDTO, resultMatcher);
    }

    @Test
    public void do_get_purchase_order_return_200() throws Exception {
        ResultMatcher resultMatcher = status().isOk();
        sendGetRequest("/fresh-products/orders/1", resultMatcher);
    }

    @Test
    public void do_not_get_purchase_order_return_404() throws Exception {
        ResultMatcher resultMatcher = status().isNotFound();
        sendGetRequest("/fresh-products/orders/195", resultMatcher);
    }
}
