package com.legkiyapps.currencyConverter.service.currency.model;

import java.sql.Timestamp;
import java.util.Map;

public record ConvertedExchangeRate(Timestamp timestamp, String source, Double amount, Map<String, Double> quotes) {
}
