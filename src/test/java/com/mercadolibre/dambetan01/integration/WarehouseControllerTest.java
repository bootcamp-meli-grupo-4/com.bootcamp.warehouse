package com.mercadolibre.dambetan01.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WarehouseControllerTest extends ControllerTestLoginMvc {
    private final String PATH = "/fresh-products/warehouse/";

    @BeforeEach
    public void setupToken() throws Exception {
        token = getToken("user_four", "contra123");
    }

    @Test
    public void do_get_product_by_warehouse_return_200() throws Exception {
        ResultMatcher resultMatcher = status().isOk();
        sendGetRequest(PATH + 1, resultMatcher);
    }

    @Test
    public void do_get_product_that_does_not_exist_by_warehouse_return_404() throws Exception {
        ResultMatcher resultMatcher = status().isNotFound();
        sendGetRequest(PATH + 111, resultMatcher);
    }

    @Test
    public void do_get_product_by_warehouse_without_representant_user_return_access_denied() throws Exception {
        token = getToken("user_one", "contra123");
        ResultMatcher[] resultMatcher = {
                status().is5xxServerError(),
                jsonPath("$.error").value("org.springframework.security.access.AccessDeniedException")
        };
        sendGetRequest(PATH + 1, resultMatcher);
    }
}
