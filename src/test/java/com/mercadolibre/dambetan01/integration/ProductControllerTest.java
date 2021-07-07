package com.mercadolibre.dambetan01.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends ControllerTestLoginMvc{
    @BeforeEach
    public void setupToken() throws Exception {
        token = getToken("user_five", "contra123");
    }

    @Test
    public void do_find_all_products_list_return_404() throws Exception {

        ResultMatcher resultMatcher = status().isNotFound();
        sendGetRequest("/fresh-products/", resultMatcher);
    }

    @Test
    public void do_find_all_products_list_category_return_404() throws Exception {

        ResultMatcher resultMatcher = status().isNotFound();
        sendGetRequest("/fresh-products/list?category=FR", resultMatcher);
    }
}
