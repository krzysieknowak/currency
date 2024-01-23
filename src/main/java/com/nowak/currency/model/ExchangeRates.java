package com.nowak.currency.model;

import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;


public class ExchangeRates {

    private List<Rate> rates;

    public ExchangeRates(){

    }
    public ExchangeRates(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }
}
