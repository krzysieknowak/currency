package com.nowak.currency.service.calculator;

import com.nowak.currency.service.NbpServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyCalculator implements CurrencyCalculatorService {
    public static final int PRECISION = 4;
    private final String warning = "Providing same currencies is not allowed";

    private final NbpServiceImpl nbpService;

    public CurrencyCalculator(NbpServiceImpl nbpService) {
        this.nbpService = nbpService;
    }

    @Override
    public BigDecimal convert(String fromCurrency, String toCurrency, BigDecimal amount) throws SameCurrenciesException {
        if(fromCurrency.equals(toCurrency)){
            throw new SameCurrenciesException(warning);
        }else if(fromCurrency == null){
            return convertFromPlnToForeign(toCurrency,amount);
        }else if(toCurrency == null){
            return convertFromForeignToPln(fromCurrency, amount);
        }
        BigDecimal fromCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(fromCurrency).getMid());
        BigDecimal toCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(toCurrency).getMid());
        return amount.multiply(fromCurrencyMidValue).divide(toCurrencyMidValue,PRECISION,RoundingMode.HALF_UP);
    }
    public BigDecimal convertFromPlnToForeign(String toCurrency, BigDecimal amount){
        BigDecimal toCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(toCurrency).getMid());
        return amount.divide(toCurrencyMidValue,PRECISION,RoundingMode.HALF_UP);
    }
    private BigDecimal convertFromForeignToPln(String fromCurrency, BigDecimal amount){
        BigDecimal fromCurrencyMidValue = stringToBigDecimal(nbpService.getRateByCode(fromCurrency).getMid());
        return amount.multiply(fromCurrencyMidValue);
    }

    private BigDecimal stringToBigDecimal(String str){
        return new BigDecimal(str);
    }
}
