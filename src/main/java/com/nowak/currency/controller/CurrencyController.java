package com.nowak.currency.controller;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import com.nowak.currency.service.CurrencyService;
import com.nowak.currency.service.NbpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

   private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rates")
    public ResponseEntity<ExchangeRates> getRates(){
        ExchangeRates rates = currencyService.getExchangeRates();
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }
}
