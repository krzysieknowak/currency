package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Service("currencyService")
public class NbpServiceImpl implements CurrencyService{

    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";


    private final RestTemplate restTemplate;

    public NbpServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Rate> getExchangeRates() {
        ResponseEntity<ExchangeRates[]> responseEntity = restTemplate.getForEntity(NBP_URL, ExchangeRates[].class);
        ExchangeRates[] exchangeRatesArray = responseEntity.getBody();

        if (exchangeRatesArray != null && exchangeRatesArray.length > 0) {
            return exchangeRatesArray[0].getRates();
        } else{
            return Collections.emptyList();
        }
    }
}
