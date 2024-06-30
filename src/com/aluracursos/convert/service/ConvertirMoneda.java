package com.aluracursos.convert.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ConvertirMoneda {

    public double convertir(String fromCurrency, String toCurrency, double amount) {
        // URL de la API de conversión de moneda
        String apiURL = "https://v6.exchangerate-api.com/v6/2dcfee1f62e76501dd92bfb1/latest/" + fromCurrency;

        try {
            // Conectar con la API
            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta de la API
            InputStream inputStream = conn.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Analizar la respuesta y extraer la tasa de cambio
            double exchangeRate = parseExchangeRate(response.toString(), toCurrency);

            // Calcular la cantidad convertida
            double convertedAmount = amount * exchangeRate;

            // Devolver la cantidad convertida
            return convertedAmount;

        } catch (IOException e) {
            e.printStackTrace(); // Manejo básico de errores
            return -1; // Indicar un valor inválido en caso de error
        }
    }

    private double parseExchangeRate(String response, String toCurrency) {
        // Este método debería analizar la respuesta de la API y extraer la tasa de cambio
        // Implementación simplificada: supondremos que la respuesta es en formato JSON
        // y que podemos extraer la tasa de cambio para la moneda de destino directamente
        String json = response.trim();
        int start = json.indexOf("\"" + toCurrency + "\":");
        if (start == -1) {
            return -1; // Indica un valor inválido si no se encuentra la tasa de cambio
        }
        start += toCurrency.length() + 3; // Para omitir ":"
        int end = json.indexOf(",", start);
        if (end == -1) {
            end = json.indexOf("}", start);
        }
        String rateString = json.substring(start, end);
        return Double.parseDouble(rateString);
    }
}
