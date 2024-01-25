package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CurrencyService {
    public List<Rate> getExchangeRates();

    Rate getRateByCode(String code);
}
