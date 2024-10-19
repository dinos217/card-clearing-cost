package com.utils.card_clearing_cost.converters;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.entities.ClearingCost;

public class ClearingCostConverter {

    public static ClearingCostDto convert(ClearingCost clearingCost) {
        return new ClearingCostDto(clearingCost.getId(), clearingCost.getCountryCode(),
                clearingCost.getClearingCost());
    }

    public static ClearingCost convert(ClearingCostDto clearingCostDto) {
        return new ClearingCost(clearingCostDto.countryCode(), clearingCostDto.clearingCost());
    }
}
