package com.utils.card_clearing_cost;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.services.BinlistService;
import com.utils.card_clearing_cost.services.ClearingCostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}) //todo: @Ignore this if I cannot find way to mock the services
public class CostCalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BinlistService binlistService;

    @Mock
    private ClearingCostService clearingCostService;

    @BeforeEach
    public void mockBinlistCall() {
        when(binlistService.getCountryCodeFromCard("4170563312345678")).thenReturn(Mono.just("IT"));
        when(clearingCostService.findByCountry("GR")).thenReturn(new ClearingCostDto("GR", new BigDecimal(15)));
    }

    @Test
    public void findClearingCostTest() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/card-cost/4170563312345678");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());
    }
}
