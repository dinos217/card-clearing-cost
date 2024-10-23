package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.converters.ClearingCostConverter;
import com.utils.card_clearing_cost.dtos.ClearingCostDto;
import com.utils.card_clearing_cost.entities.ClearingCost;
import com.utils.card_clearing_cost.exceptions.AlreadyExistsException;
import com.utils.card_clearing_cost.exceptions.NotFoundException;
import com.utils.card_clearing_cost.repositories.ClearingCostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClearingCostServiceImpl implements ClearingCostService {

    private final ClearingCostRepository clearingCostRepository;

    @Autowired
    public ClearingCostServiceImpl(ClearingCostRepository clearingCostRepository) {
        this.clearingCostRepository = clearingCostRepository;
    }

    @Override
    @Transactional
    public ClearingCostDto save(ClearingCostDto clearingCostDto) {

        if (clearingCostRepository.existsByCountryCode(clearingCostDto.countryCode())) {
            throw new AlreadyExistsException("Clearing cost for country code " + clearingCostDto.countryCode() +
                    " already exists.");
        }

        ClearingCost clearingCostToBeSaved = ClearingCostConverter.convert(clearingCostDto);
        ClearingCost clearingCost = clearingCostRepository.save(clearingCostToBeSaved);

        return ClearingCostConverter.convert(clearingCost);
    }

    @Override
    @Transactional
    public ClearingCostDto findByCountry(String countryCode) {

        ClearingCost clearingCost = clearingCostRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new NotFoundException("Could not find country code " + countryCode));

        return ClearingCostConverter.convert(clearingCost);
    }

    @Override
    public List<ClearingCostDto> findAll() {

        List<ClearingCost> clearingCostList = clearingCostRepository.findAll();

        return clearingCostList.stream().map(ClearingCostConverter::convert).toList();
    }

    @Override
    @Transactional
    public ClearingCostDto update(ClearingCostDto clearingCostDto) {

        ClearingCost clearingCostToBeUpdated = clearingCostRepository.findByCountryCode(clearingCostDto.countryCode())
                .orElseThrow(() -> new NotFoundException("Could not find country code "
                        + clearingCostDto.countryCode()));

        clearingCostToBeUpdated.setCountryCode(clearingCostDto.countryCode());
        clearingCostToBeUpdated.setClearingCost(clearingCostDto.clearingCost());

        ClearingCost clearingCost = clearingCostRepository.save(clearingCostToBeUpdated);

        return ClearingCostConverter.convert(clearingCost);
    }

    @Override
    public void delete(String countryCode) {

        ClearingCost clearingCost = clearingCostRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new NotFoundException("Could not find country code " + countryCode));

        clearingCostRepository.delete(clearingCost);
    }
}
