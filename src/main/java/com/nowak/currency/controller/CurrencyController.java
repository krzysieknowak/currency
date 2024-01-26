package com.nowak.currency.controller;

import com.nowak.currency.model.Rate;
import com.nowak.currency.service.CurrencyService;
import com.nowak.currency.service.calculator.CurrencyCalculatorService;
import com.nowak.currency.service.calculator.SameCurrenciesException;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyCalculatorService calculatorService;

    public CurrencyController(CurrencyService currencyService, CurrencyCalculatorService calculatorService) {
        this.currencyService = currencyService;
        this.calculatorService = calculatorService;
    }

    @GetMapping("/rates")
    public List<Rate> getRates() {
        return currencyService.getExchangeRates();
    }

    @GetMapping("/rates/{code}")
    public Rate getRateByCode(@PathVariable String code) {
        return currencyService.getRateByCode(code);
    }

    @GetMapping("/convert")
    public BigDecimal convertCurrency(@RequestParam(required = false) String fromCurrency,
                                      @RequestParam(required = false) String toCurrency,
                                      @RequestParam BigDecimal amount) throws SameCurrenciesException {
        return calculatorService.convert(fromCurrency, toCurrency, amount);
    }
}
