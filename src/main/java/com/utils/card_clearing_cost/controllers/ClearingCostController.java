package com.utils.card_clearing_cost.controllers;

import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.services.ClearingCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clearing-cost")
public class ClearingCostController {

    private final ClearingCostService clearingCostService;

    @Autowired
    public ClearingCostController(ClearingCostService clearingCostService) {
        this.clearingCostService = clearingCostService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClearingCostDto> save(@RequestBody ClearingCostDto clearingCostDto) {

        //todo: add logger if i have time
        //logger.info("Started saving clearing cost...");
        return ResponseEntity.status(HttpStatus.OK).body(clearingCostService.save(clearingCostDto));
    }

    @GetMapping(value = "/{countryCode}")
    public ResponseEntity<ClearingCostDto> getByCountry(@PathVariable String countryCode) {

        return ResponseEntity.status(HttpStatus.OK).body(clearingCostService.findByCountry(countryCode));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ClearingCostDto>> getByCountry() {

        return ResponseEntity.status(HttpStatus.OK).body(clearingCostService.findAll());
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClearingCostDto> update(@RequestBody ClearingCostDto clearingCostDto) {

        //todo: add logger if i have time
        //logger.info("Started saving clearing cost...");
        return ResponseEntity.status(HttpStatus.OK).body(clearingCostService.update(clearingCostDto));
    }

    @DeleteMapping("/{countryCode}")
    ResponseEntity<String> delete(@PathVariable String countryCode) {
        clearingCostService.delete(countryCode);
        return ResponseEntity.status(HttpStatus.OK).body("Clearing cost for " + countryCode +
                " was deleted successfully.");
    }
}
