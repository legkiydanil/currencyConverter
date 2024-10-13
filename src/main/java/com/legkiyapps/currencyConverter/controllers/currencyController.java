package com.legkiyapps.currencyConverter.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.legkiyapps.currencyConverter.CurrencyRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.legkiyapps.currencyConverter.CurrencyRates.sendLiveRequest;


@RestController
public class currencyController {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/name")
    public String getNameApplication() {
        return name;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/rates")
    public String getRates() throws JsonProcessingException {
        CurrencyRates currencyRates = new CurrencyRates();
        String jsonData = objectMapper.writeValueAsString(currencyRates);
        return jsonData;
    }

}
