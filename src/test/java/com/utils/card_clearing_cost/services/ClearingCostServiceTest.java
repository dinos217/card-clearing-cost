package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.entities.ClearingCost;
import com.utils.card_clearing_cost.exceptions.NotFoundException;
import com.utils.card_clearing_cost.repositories.ClearingCostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClearingCostServiceTest {

    @Mock
    private ClearingCostRepository clearingCostRepository;

    @InjectMocks
    private ClearingCostServiceImpl clearingCostService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClearingCost() {
        ClearingCostDto dto = new ClearingCostDto("ESP", new BigDecimal(5));

        ClearingCost clearingCost = buildClearingCost();

        when(clearingCostRepository.save(any(ClearingCost.class))).thenReturn(clearingCost);

        ClearingCostDto result = clearingCostService.save(dto);

        assertEquals("ESP", result.countryCode());
        assertEquals(new BigDecimal(5), result.clearingCost());
    }

    @Test
    void testFindByCountry() {
        ClearingCost clearingCost = new ClearingCost();
        clearingCost.setId(1L);
        clearingCost.setCountryCode("ESP");
        clearingCost.setClearingCost(new BigDecimal(5));

        when(clearingCostRepository.findByCountryCode("ESP")).thenReturn(Optional.of(clearingCost));

        ClearingCostDto result = clearingCostService.findByCountry("ESP");

        assertEquals("ESP", result.countryCode());
        assertEquals(new BigDecimal(5), result.clearingCost());
    }

    @Test
    void testFindAll() {
        ClearingCost clearingCost1 = buildClearingCost();

        ClearingCost clearingCost2 = new ClearingCost();
        clearingCost2.setId(2L);
        clearingCost2.setCountryCode("FRA");
        clearingCost2.setClearingCost(new BigDecimal(6));

        List<ClearingCost> list = Arrays.asList(clearingCost1, clearingCost2);

        when(clearingCostRepository.findAll()).thenReturn(list);

        assertEquals(2, list.size());
        assertEquals("FRA", list.get(1).getCountryCode());
        assertEquals(new BigDecimal(6), list.get(1).getClearingCost());
    }

    @Test
    void testUpdateClearingCost() {
        ClearingCost clearingCost = buildClearingCost();

        when(clearingCostRepository.findByCountryCode("ESP")).thenReturn(Optional.of(clearingCost));

        clearingCost.setCountryCode("CRO");

        when(clearingCostRepository.save(any(ClearingCost.class))).thenReturn(clearingCost);

        ClearingCostDto dto = new ClearingCostDto("CRO", new BigDecimal(5));

        ClearingCostDto result = clearingCostService.save(dto);

        assertEquals("CRO", result.countryCode());
    }

    @Test
    void testDeleteClearingCost() {
        ClearingCost clearingCost = buildClearingCost();

        when(clearingCostRepository.findByCountryCode("ESP")).thenReturn(Optional.of(clearingCost));

        clearingCostService.delete("ESP");

        verify(clearingCostRepository).delete(clearingCost);
    }

    @Test
    void testDeleteClearingCostCountryNotFound() {
        when(clearingCostRepository.findByCountryCode("ABC")).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> clearingCostService.delete("ABC"));

        assertEquals("Could not find country code ABC", notFoundException.getMessage());
    }

    private static ClearingCost buildClearingCost() {
        ClearingCost clearingCost = new ClearingCost();
        clearingCost.setId(1L);
        clearingCost.setCountryCode("ESP");
        clearingCost.setClearingCost(new BigDecimal(5));
        return clearingCost;
    }
}
