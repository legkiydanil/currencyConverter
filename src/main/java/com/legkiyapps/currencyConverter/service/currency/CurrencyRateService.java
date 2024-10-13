package com.legkiyapps.currencyConverter.service.currency;

import com.legkiyapps.currencyConverter.service.currency.CurrencyLayerClient.CurrencyLayerClientConfig;
import com.legkiyapps.currencyConverter.service.currency.model.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyLayerClientConfig config;
    private final CurrencyLayerClient currencyLayerClient;

    public ExchangeRate getExchangeRate(List<String> currencies, String source) {
        return currencyLayerClient.getExchageRate(currencies, source, config.apiKey());
    }
}
