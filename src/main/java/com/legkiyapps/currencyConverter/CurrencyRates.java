package com.legkiyapps.currencyConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyRates {


    public static final String ACCESS_KEY = "d0f9ac679bbb43417768123ff2170531";
    public static final String BASE_URL = "http://api.currencylayer.com/";
    public static final String ENDPOINT = "live";
    public static final String SOURCE = "USD";
    public static final String CURRENCIES = "GBP,RUB,KZT,CNY";


    static CloseableHttpClient httpClient = HttpClients.createDefault();



    public static void sendLiveRequest(){

        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY
                + "&currencies=" + CURRENCIES + "&source=" + SOURCE);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = dateFormat.format(timeStampDate);
            String RUB = ("1 " + exchangeRates.getString("source") + " in RUB: "
                               + exchangeRates.getJSONObject("quotes").getDouble("USDRUB")
                               + " (Date: " + formattedDate + ")");
            String KZT = ("1 " + exchangeRates.getString("source") + " in KZT: "
                          + exchangeRates.getJSONObject("quotes").getDouble("USDKZT")
                          + " (Date: " + formattedDate + ")");
            String CNY = ("1 " + exchangeRates.getString("source") + " in CNY: "
                          + exchangeRates.getJSONObject("quotes").getDouble("USDCNY")
                          + " (Date: " + formattedDate + ")");
            String GBP = ("1 " + exchangeRates.getString("source") + " in GBP: "
                          + exchangeRates.getJSONObject("quotes").getDouble("USDGBP")
                          + " (Date: " + formattedDate + ")");

            System.out.println(RUB);
            System.out.println(KZT);
            System.out.println(CNY);
            System.out.println(GBP);

            response.close();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        sendLiveRequest();
        httpClient.close();
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}





