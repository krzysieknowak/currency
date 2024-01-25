package com.nowak.currency.service.calculator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public interface CurrencyCalculatorService {

    BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount);
}
