package com.utils.card_clearing_cost.dtos;

public class BinlistResponse {

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static class Country {
        private String alpha2;

        // Getters and setters
        public String getAlpha2() {
            return alpha2;
        }

        public void setAlpha2(String alpha2) {
            this.alpha2 = alpha2;
        }
    }
}
