package com.legkiyapps.currencyConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyRates {
    private static final String API_KEY = "d0f9ac679bbb43417768123ff2170531"; //
    private static final String BASE_URL = "https://api.currencylayer.com/live";

    public static void main(String[] args) throws IOException, JSONException {
        // source - валюта по умолчанию, котрую мы будем конвертировать
        String source = "USD";
        String currencies = "USD,EUR,CNY,KZT";


        String apiUrl = constructApiUrl(source, currencies);

        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.readLine();

        JSONObject jsonResponse = new JSONObject(response);
        String quotes = jsonResponse.getString("quotes");

        String result = "Курс валюты " + source + " на данный момент - " + quotes;
        System.out.println(result);
    }


    private static String constructApiUrl(String currencies, String source) {
        return BASE_URL + "?access_key=" + API_KEY + "&currencies=" + currencies + "&source=" + source + "&format=1";
    }
}