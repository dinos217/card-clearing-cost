package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.exceptions.BinlistException;
import com.utils.card_clearing_cost.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CostCalculationServiceTest {

    @Mock
    private ClearingCostService clearingCostService;

    @Mock
    private BinlistService binlistService;

    @InjectMocks
    private CostCalculationServiceImpl costCalculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindClearingCost() {
        when(binlistService.getCountryCodeFromCard("12345678")).thenReturn(Mono.just("GR"));

        ClearingCostDto dto = new ClearingCostDto("GR", new BigDecimal(15));
        when(clearingCostService.findByCountry("GR")).thenReturn(dto);

        ClearingCostDto response = costCalculationService.findClearingCost("1234567890");
        assertEquals(response.countryCode(), "GR");
    }

    @Test
    void testFindClearingCostClearingCostServiceThrowsException() {

        when(binlistService.getCountryCodeFromCard("12345678")).thenReturn(Mono.just("GR"));
        when(clearingCostService.findByCountry("GR")).thenThrow(new NotFoundException("not found"));

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> costCalculationService.findClearingCost("12345678"));

        assertEquals("not found", notFoundException.getMessage());
    }

    @Test
    void testFindClearingCostBinlistServiceThrowsException() {

        when(binlistService.getCountryCodeFromCard("12345678"))
                .thenThrow(new RuntimeException("exception from Binlist"));

        BinlistException binlistException = assertThrows(BinlistException.class,
                () -> costCalculationService.findClearingCost("12345678"));

        assertEquals("exception from Binlist", binlistException.getMessage());
    }
}
