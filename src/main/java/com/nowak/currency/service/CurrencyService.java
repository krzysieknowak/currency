package com.nowak.currency.service;

import com.nowak.currency.model.Rate;

import java.util.List;


public interface CurrencyService {
    public List<Rate> getExchangeRates();

    Rate getRateByCode(String code);
}
