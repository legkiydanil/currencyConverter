package com.legkiyapps.currencyConverter.service.currency;

import com.legkiyapps.currencyConverter.service.currency.model.ExchangeRate;
import feign.Request;
import feign.Request.Options;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "currency-layer", url = "${rest.currency-layer.url}", configuration = CurrencyLayerClient.RestConfiguration.class)
public interface CurrencyLayerClient {

    @GetMapping("/live")
    ExchangeRate getExchageRate(@RequestParam("currencies") List<String> currencies,
                                @RequestParam("source") String source,
                                @RequestParam("access_key") String accessKey);

    class RestConfiguration {
        @Bean
        public Request.Options options(CurrencyLayerClientConfig config) {
            return new Options(config.connectTimeout(), config.readTimeout()); //да он deprecated, можно и лучше настроить но для примера пойдёт
        }
    }

    @ConfigurationProperties("rest.currency-layer")
    record CurrencyLayerClientConfig(String apiKey, String url, int connectTimeout, int readTimeout) {
    }
}
