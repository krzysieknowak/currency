package com.nowak.currency.service;

import com.nowak.currency.model.ExchangeRates;
import com.nowak.currency.model.Rate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class NbpServiceImplTest {

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final NbpServiceImpl nbpService = new NbpServiceImpl(restTemplate);

    @Test
    void shouldReturnCorrectRatesWhenUsingGetExchangeRatesMethod() {
        //given
        Rate usd = new Rate("USD", "4.0393");
        Rate eur = new Rate("EUR", "4.3802");
        ExchangeRates mockedRates = new ExchangeRates();
        mockedRates.setRates(List.of(usd, eur));
        ResponseEntity<ExchangeRates[]> responseEntity = new ResponseEntity<>(new ExchangeRates[]{mockedRates}, HttpStatus.OK);
        when(restTemplate.getForEntity(NbpServiceImpl.NBP_URL, ExchangeRates[].class)).thenReturn(responseEntity);
        //when
        List<Rate> exchangeRatesResult = nbpService.getExchangeRates();
        //then
        assertThat(exchangeRatesResult.size()).isEqualTo(2);
        assertThat(exchangeRatesResult).containsExactly(usd, eur);
        assertThat(exchangeRatesResult).isNotNull();
    }

    @Test
    void shouldReturnCorrectRateWhenUsingGetRateByCodeMethod() {
        //given
        String code = "EUR";
        Rate eur = new Rate("EUR", "4.3802");
        ExchangeRates mockedRates = new ExchangeRates();
        mockedRates.setRates(List.of(eur));
        ResponseEntity<ExchangeRates[]> responseEntity = new ResponseEntity<>(new ExchangeRates[]{mockedRates}, HttpStatus.OK);
        when(restTemplate.getForEntity(NbpServiceImpl.NBP_URL, ExchangeRates[].class)).thenReturn(responseEntity);
        //when
        Rate rateByCodeResult = nbpService.getRateByCode(code);
        //then
        assertThat(rateByCodeResult.getCode()).isEqualTo("EUR");
        assertThat(rateByCodeResult.getMid()).isEqualTo("4.3802");

    }
}