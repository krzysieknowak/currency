package com.nowak.currency.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRates {

    private List<Rate> rates;
}
