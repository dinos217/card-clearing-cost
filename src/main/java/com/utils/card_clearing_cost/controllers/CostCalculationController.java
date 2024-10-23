package com.utils.card_clearing_cost.controllers;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.services.CostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/card-cost")
public class CostCalculationController {

    private final CostCalculationService costCalculationService;

    @Autowired
    public CostCalculationController(CostCalculationService costCalculationService) {
        this.costCalculationService = costCalculationService;
    }

    @GetMapping(value = "/{cardNumber}")
    public ResponseEntity<ClearingCostDto> getCardCost(@PathVariable String cardNumber) {

        return ResponseEntity.status(HttpStatus.OK).body(costCalculationService.findClearingCost(cardNumber));
    }
}
