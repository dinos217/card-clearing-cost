package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.exceptions.BadRequestException;
import com.utils.card_clearing_cost.exceptions.BinlistException;
import com.utils.card_clearing_cost.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CostCalculationServiceImpl implements CostCalculationService {

    public static final int MIN_DIGITS = 8;
    public static final int MAX_DIGITS = 19;

    private final ClearingCostService clearingCostService;
    private final BinlistService binlistService;

    public CostCalculationServiceImpl(ClearingCostService clearingCostService, BinlistService binlistService) {
        this.clearingCostService = clearingCostService;
        this.binlistService = binlistService;
    }

    @Override
    public ClearingCostDto findClearingCost(String cardNumber) {

        if (cardNumber.length() < MIN_DIGITS || cardNumber.length() > MAX_DIGITS) {
            throw new BadRequestException("Card number digits are incorrect.");
        }

        cardNumber = cardNumber.substring(0,8);

        try {
            String countryCode = binlistService.getCountryCodeFromCard(cardNumber).block();
            return clearingCostService.findByCountry(countryCode);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        } catch (RuntimeException e) {
           throw new BinlistException(e.getMessage());
        }
    }
}
