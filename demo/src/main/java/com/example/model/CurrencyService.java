package com.example.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyService {

    public static double getEURtoUSD() {
    try {
        URL url = new URL("https://api.frankfurter.app/latest?from=EUR&to=USD");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = br.readLine();

        JSONObject json = new JSONObject(response);
        double rate = json.getJSONObject("rates").getDouble("USD");

        System.out.println("API EUR→USD rate: " + rate); // debug

        return rate;

    } catch (Exception e) {
        System.out.println("Error getting exchange rate: " + e.getMessage());
        return 1.0; // fallback
    }
}

}
