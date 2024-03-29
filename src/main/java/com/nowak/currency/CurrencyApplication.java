package com.nowak.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class CurrencyApplication {


	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
	}


}
