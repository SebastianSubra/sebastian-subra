package com.example.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyService {

    // Cache
    private static double cachedRate = -1.0;
    private static long lastFetchTime = 0L;

    // 24 horas en milisegundos
    private static final long CACHE_DURATION = 24L * 60 * 60 * 1000;

    public static double getEURtoUSD() {

        long now = System.currentTimeMillis();

        // Si tenemos un valor y no han pasado 24h, usar cache
        if (cachedRate > 0 && (now - lastFetchTime) < CACHE_DURATION) {
            System.out.println("Using cached EUR→USD rate: " + cachedRate);
            return cachedRate;
        }

        // Si no, llamar a la API
        try {
            URL url = new URL("https://api.frankfurter.app/latest?from=EUR&to=USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            String response = br.readLine();

            JSONObject json = new JSONObject(response);
            double rate = json.getJSONObject("rates").getDouble("USD");

            // Guardar en cache
            cachedRate = rate;
            lastFetchTime = now;

            System.out.println("Fetched new EUR→USD rate from API: " + rate);

            return rate;

        } catch (Exception e) {
            System.out.println("Error getting exchange rate: " + e.getMessage());

            // Si falla la API pero tenemos un valor antiguo, usarlo
            if (cachedRate > 0) {
                System.out.println("Using last cached rate due to error: " + cachedRate);
                return cachedRate;
            }

            return 1.0; // fallback final
        }
    }
}
