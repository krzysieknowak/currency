package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NBPService {

    private static final String NBP_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private final WebClient webClient;

    public NBPService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Cacheable("exchangeRatesCache")
    public Flux<ExchangeRates> getResponse(){
        return webClient.get()
                .uri(NBP_URL)
                .retrieve()
                .bodyToFlux(ExchangeRates.class);
    }

}
