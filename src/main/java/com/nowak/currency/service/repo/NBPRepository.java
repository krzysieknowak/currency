package com.nowak.currency.service.repo;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.service.NBPService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class NBPRepository {

    ExchangeRates rates;

    private final NBPService nbpService;

    public NBPRepository(NBPService nbpService) {
        this.nbpService = nbpService;
    }



}
