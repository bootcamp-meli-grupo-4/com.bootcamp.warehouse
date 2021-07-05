package com.mercadolibre.dambetan01.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.dambetan01.dtos.response.AccountResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ControllerTestLoginMvc extends ControllerTest {

    private static final String PATH = "/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    protected String token = null;

    private String lastUsername;

    private String lastPassword;

    protected String getToken(String username, String password) throws Exception {
        if(username.equals(lastUsername) && lastPassword.equals(password) && token != null) return token;

        lastUsername = username;
        lastPassword = password;

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(PATH + "/sign-in")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", username)
                .param("password", password))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

        AccountResponseDTO accountResponseDTO = objectMapper.readValue(
                result.getResponse().getContentAsByteArray(), AccountResponseDTO.class
        );

        return accountResponseDTO.getToken();
    }

    protected void sendPostRequest(String path, Object content, ResultMatcher... expected) throws Exception {
        ResultActions action = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(PATH + path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(content))
                        .header("Authorization", token)
        )
                .andDo(MockMvcResultHandlers.print());

        for (ResultMatcher matcher : expected)
            action.andExpect(matcher);
    }

    protected void sendPutRequest(String path, Object content, ResultMatcher... expected) throws Exception {
        ResultActions action = mockMvc.perform(
                MockMvcRequestBuilders
                        .put(PATH + path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(content))
                        .header("Authorization", token)
        )
                .andDo(MockMvcResultHandlers.print());

        for (ResultMatcher matcher : expected)
            action.andExpect(matcher);
    }
}
