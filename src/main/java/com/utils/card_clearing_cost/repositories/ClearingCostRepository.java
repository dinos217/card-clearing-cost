package com.utils.card_clearing_cost.repositories;

import com.utils.card_clearing_cost.entities.ClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClearingCostRepository extends JpaRepository<ClearingCost, Long> {

    Optional<ClearingCost> findByCountryCode(String countryCode);
}
