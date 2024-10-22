package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.exceptions.BadRequestException;
import com.utils.card_clearing_cost.exceptions.BinlistException;
import org.springframework.stereotype.Service;

@Service
public class CostCalculationServiceImpl implements CostCalculationService {

    private final ClearingCostService clearingCostService;
    private final BinlistService binlistService;

    public CostCalculationServiceImpl(ClearingCostService clearingCostService, BinlistService binlistService) {
        this.clearingCostService = clearingCostService;
        this.binlistService = binlistService;
    }

    @Override
    public ClearingCostDto findClearingCost(String cardNumber) {

        if (cardNumber.length() < 8 || cardNumber.length() > 19) {
            throw new BadRequestException("Card number is incorrect.");
        }

        cardNumber = cardNumber.substring(0,8);

        try {
            String countryCode = binlistService.getCountryCodeFromCard(cardNumber).block();
            return clearingCostService.findByCountry(countryCode);
        } catch (RuntimeException e) {
           throw new BinlistException(e.getMessage());
        }
    }
}
