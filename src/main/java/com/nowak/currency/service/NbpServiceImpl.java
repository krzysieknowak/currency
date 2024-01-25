package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Service("currencyService")
public class NbpServiceImpl implements CurrencyService{

    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private List<Rate> exchangeRates;
    private final RestTemplate restTemplate;

    public NbpServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Rate> getExchangeRates() {
        if(exchangeRates == null){
            ResponseEntity<ExchangeRates[]> responseEntity = restTemplate.getForEntity(NBP_URL, ExchangeRates[].class);
            ExchangeRates[] exchangeRatesArray = responseEntity.getBody();

            if (exchangeRatesArray != null && exchangeRatesArray.length > 0) {
                exchangeRates = exchangeRatesArray[0].getRates();
            } else{
                exchangeRates = Collections.emptyList();
            }
            return exchangeRates;
        }
        return exchangeRates;
    }

    @Override
    public Rate getRateByCode(String code) {
        return exchangeRates.stream()
                .filter(rate -> rate.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Rates not found for code " + code));
    }
}
