package com.legkiyapps.currencyConverter.service.currency.model;

import java.sql.Timestamp;
import java.util.Map;

public record ExchangeRate(Timestamp timestamp, String source, Map<String, Double> quotes) {
}