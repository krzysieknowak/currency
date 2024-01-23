package com.nowak.currency.controller;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import com.nowak.currency.service.CurrencyService;
import com.nowak.currency.service.NBPService;
import com.nowak.currency.service.repo.NBPRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    private final NBPService nbpService;
    private final CurrencyService currencyService;

    public CurrencyController(NBPService nbpService, CurrencyService currencyService) {
        this.nbpService = nbpService;
        this.currencyService = currencyService;
    }
    @GetMapping("/rates")
    public Flux<ExchangeRates> getExchangeRates(){
        return nbpService.getResponse();
    }
}
