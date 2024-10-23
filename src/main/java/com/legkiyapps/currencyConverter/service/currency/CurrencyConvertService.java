package com.legkiyapps.currencyConverter.service.currency;

import com.legkiyapps.currencyConverter.service.currency.model.ExchangeRate;
import com.legkiyapps.currencyConverter.service.currency.model.ConvertedExchangeRate;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CurrencyConvertService {

   private final Cache<String, Double> currencyCache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)  // Время жизни записи — 60 минут
            .maximumSize(1000)  // Максимальный размер кэша
            .build();

   private final Cache<String, Double> convertedCurrencyCache = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.MINUTES)  // Время жизни записи — 60 минут
            .maximumSize(1000)  // Максимальный размер кэша
            .build();

   /* public ConvertedExchangeRate convertExchangeRate(@NonNull ExchangeRate exchangeRate, @NonNull List<String> currencies, double amount) {

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
*/

    public ConvertedExchangeRate convertExchangeRate(@NonNull ExchangeRate exchangeRate, @NonNull List<String> currencies, double amount) {

        String currencyToConvert = exchangeRate.source() + currencies.getFirst();

        // Ищем курс в кэше
        Double currentRate = currencyCache.getIfPresent(currencyToConvert);

        // Если курса нет в кэше, получаем его из ExchangeRate и добавляем в кэш
        if (currentRate == null) {
            currentRate = exchangeRate.quotes().get(currencyToConvert);
            if (currentRate == null) {
                throw new IllegalArgumentException("Курс для пары " + currencyToConvert + " не найден.");
            }

            currencyCache.put(currencyToConvert, currentRate);
        }


        double convertedValue = currentRate * amount;
        convertedCurrencyCache.put(currencyToConvert, convertedValue);


        return new ConvertedExchangeRate(exchangeRate.timestamp(), exchangeRate.source(), amount, convertedCurrencyCache.asMap());
    }

}

