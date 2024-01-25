package com.nowak.currency.service.calculator;

import com.nowak.currency.model.Rate;
import com.nowak.currency.service.NbpServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyCalculator implements CurrencyCalculatorService {
    public static final int PRECISION = 4;

    private final NbpServiceImpl nbpService;

    public CurrencyCalculator(NbpServiceImpl nbpService) {
        this.nbpService = nbpService;
    }

    @Override
    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) {
        if(fromCurrency == null){
            return convertFromPlnToForeign(toCurrency,amount);
        }
        BigDecimal fromCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(fromCurrency).getMid());
        BigDecimal toCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(toCurrency).getMid());
        return amount.multiply(fromCurrencyMidValue).divide(toCurrencyMidValue,PRECISION,RoundingMode.HALF_UP);
    }
    private BigDecimal convertFromPlnToForeign(String toCurrency, BigDecimal amount){
        List<Rate> rates = nbpService.getExchangeRates();
        BigDecimal toCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(toCurrency).getMid());
        return amount.divide(toCurrencyMidValue,PRECISION,RoundingMode.HALF_UP);
    }

    private BigDecimal stringToBigDecimal(String str){
        return new BigDecimal(str);
    }
}
