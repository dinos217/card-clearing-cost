package com.utils.card_clearing_cost.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClearingCostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ObjectWriter objectWriter = objectMapper.writer();

    @BeforeEach
    public void setup() throws Exception {

        ClearingCostDto clearingCostDto = new ClearingCostDto("CRO", new BigDecimal(5));
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/clearing-cost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clearingCostDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void saveClearingCostTest() throws Exception {

        ClearingCostDto clearingCostDto = new ClearingCostDto("ESP", new BigDecimal(5));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/v1/clearing-cost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clearingCostDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findClearingCostTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/v1/clearing-cost/CRO");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findNonExistentClearingCostTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/clearing-cost/ABC"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void findAllClearingCostTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/v1/clearing-cost/all");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateClearingCostTest() throws Exception {

        ClearingCostDto clearingCostDto = new ClearingCostDto("CRO", new BigDecimal(6));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/v1/clearing-cost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clearingCostDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteClearingCostTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/v1/clearing-cost/CRO");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String asJsonString(Object o) throws JsonProcessingException {
        return objectWriter.writeValueAsString(o);
    }
}
