package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;

public interface CostCalculationService {

    ClearingCostDto findClearingCost(String cardNumber);
}
