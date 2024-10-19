package com.legkiyapps.currencyConverter.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.legkiyapps.currencyConverter.service.currency.CurrencyConvertService;
import com.legkiyapps.currencyConverter.service.currency.CurrencyRateService;
import com.legkiyapps.currencyConverter.service.currency.model.ConvertedExchangeRate;
import com.legkiyapps.currencyConverter.service.currency.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CurrencyController {

    @Value("${spring.application.name}")
    private String name;
    private final CurrencyRateService currencyRateService;
    private final CurrencyConvertService currencyConvertService;

    @GetMapping("/name")
    public String getNameApplication() {
        return name;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/rates")
    public ExchangeRate getRates(@RequestParam List<String> currencies, @RequestParam String source) {
        return currencyRateService.getExchangeRate(currencies, source);
    }

    @GetMapping("/convert")
    public ConvertedExchangeRate getConvertedRates(@RequestParam List<String> currencies, @RequestParam String source, @RequestParam double amount) {
        ExchangeRate exchangeRate = currencyRateService.getExchangeRate(currencies, source);
        return currencyConvertService.convertExchangeRate(exchangeRate, currencies, amount);
    }
}
