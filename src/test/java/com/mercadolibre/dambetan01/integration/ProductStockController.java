package com.mercadolibre.dambetan01.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductStockController extends ControllerTestLoginMvc{

    @BeforeEach
    public void setupToken() throws Exception {
        token = getToken("user_three", "contra123");
    }


    @Test
    public void do_find_all_products_due_date_by_sector_return_200() throws Exception {

        ResultMatcher resultMatcher = status().isOk();
        sendGetRequest("/fresh-products/due-date/?idSector=" + 1, resultMatcher);
    }

    @Test
    public void do_find_all_products_due_date_by_sector_return_404() throws Exception {

        ResultMatcher resultMatcher = status().isNotFound();
        sendGetRequest("/fresh-products/due-date/?idSector=" + 3, resultMatcher);
    }
}
