package com.utils.card_clearing_cost.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "clearing_cost")
public class ClearingCost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Column(name = "clearing_cost", nullable = false)
    private BigDecimal clearingCost;

    public ClearingCost() {}

    public ClearingCost(String countryCode, BigDecimal clearingCost) {
        this.countryCode = countryCode;
        this.clearingCost = clearingCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getClearingCost() {
        return clearingCost;
    }

    public void setClearingCost(BigDecimal clearingCost) {
        this.clearingCost = clearingCost;
    }
}
