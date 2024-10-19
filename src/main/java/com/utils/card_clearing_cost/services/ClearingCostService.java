package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;

import java.util.List;

public interface ClearingCostService {

    ClearingCostDto save(ClearingCostDto clearingCostDto);
    ClearingCostDto findByCountry(String countryCode);
    List<ClearingCostDto> findAll();
    ClearingCostDto update(ClearingCostDto clearingCostDto);
    void delete(String countryCode);
}
