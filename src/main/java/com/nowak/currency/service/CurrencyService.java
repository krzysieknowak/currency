package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyService {


    public BigDecimal convertCurrency(BigDecimal amount, String fromCurrency, String toCurrency){

        return BigDecimal.ONE;
    }
}
