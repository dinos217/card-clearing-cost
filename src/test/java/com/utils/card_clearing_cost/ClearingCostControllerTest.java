package com.utils.card_clearing_cost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.utils.card_clearing_cost.dtos.ClearingCostDto;
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
public class ClearingCostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ObjectWriter objectWriter = objectMapper.writer();

    @Test
    public void saveClearingCostTest() throws Exception {

        ClearingCostDto clearingCostDto = new ClearingCostDto(null, "CRO", new BigDecimal(5));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/clearing-cost")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(clearingCostDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findClearingCostTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/clearing-cost/CRO");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String asJsonString(Object o) throws JsonProcessingException {
        return objectWriter.writeValueAsString(o);
    }
}
