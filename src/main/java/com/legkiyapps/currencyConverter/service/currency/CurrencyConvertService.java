package com.legkiyapps.currencyConverter.service.currency;

import com.legkiyapps.currencyConverter.service.currency.model.ExchangeRate;
import com.legkiyapps.currencyConverter.service.currency.model.ConvertedExchangeRate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyConvertService {

    public ConvertedExchangeRate convertExchangeRate(@NonNull ExchangeRate exchangeRate, @NonNull List<String> currencies, double amount) {

        Map<String, Double> convertedQuotes = new HashMap<>(exchangeRate.quotes());

        String currencyToConvert = exchangeRate.source() + currencies.getFirst();

        Double currentRate = convertedQuotes.get(currencyToConvert);
        if (currentRate == null) {
            throw new IllegalArgumentException("Курс для пары " + currencyToConvert + " не найден.");
        }


        double newValue = currentRate * amount;

        convertedQuotes.put(currencyToConvert, newValue);

        return new ConvertedExchangeRate(exchangeRate.timestamp(), exchangeRate.source(), amount, convertedQuotes);
    }
}
