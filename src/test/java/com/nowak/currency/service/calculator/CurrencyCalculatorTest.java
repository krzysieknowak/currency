package com.nowak.currency.service.calculator;

import com.nowak.currency.model.Rate;
import com.nowak.currency.service.NbpServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CurrencyCalculatorTest {

    private final NbpServiceImpl nbpService = Mockito.mock(NbpServiceImpl.class);
    private final CurrencyCalculator currencyCalculator = new CurrencyCalculator(nbpService);


    @Test
    void shouldReturnCorrectValueWhenConvertingFromPlnToUsd() throws SameCurrenciesException {
        //given
        BigDecimal amount = new BigDecimal(10);
        String toCurrency = "USD";
        //when
        when(nbpService.getRateByCode(toCurrency)).thenReturn(new Rate("USD", "4.0393"));
        BigDecimal result = currencyCalculator.convert(null, toCurrency, amount);
        //then
        assertThat(result).isEqualTo(new BigDecimal("2.4757"));
    }
    @Test
    void shouldReturnCorrectValueWhenConvertingFromUsdToPln() throws SameCurrenciesException {
        //given
        BigDecimal amount = new BigDecimal(10);
        String fromCurrency = "USD";
        //when
        when(nbpService.getRateByCode(fromCurrency)).thenReturn(new Rate("USD", "4.0393"));
        BigDecimal result = currencyCalculator.convert(fromCurrency,null, amount);
        //then
        assertThat(result).isEqualTo(new BigDecimal("40.3930"));
    }

    @Test
    void shouldReturnCorrectValueWhenConvertingFromUsdToEur() throws SameCurrenciesException {
        //given
        BigDecimal amount = new BigDecimal(10);
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        //when
        when(nbpService.getRateByCode(fromCurrency)).thenReturn(new Rate("USD", "4.0393"));
        when(nbpService.getRateByCode(toCurrency)).thenReturn(new Rate("EUR", "4.3802"));
        BigDecimal result = currencyCalculator.convert(fromCurrency,toCurrency, amount);
        //then
        assertThat(result).isEqualTo(new BigDecimal("9.2217"));
    }

}