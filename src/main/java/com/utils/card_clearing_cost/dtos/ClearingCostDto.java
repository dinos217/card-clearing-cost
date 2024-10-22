package com.utils.card_clearing_cost.dtos;

import java.math.BigDecimal;

public record ClearingCostDto(
        String countryCode,
        BigDecimal clearingCost
) {}
