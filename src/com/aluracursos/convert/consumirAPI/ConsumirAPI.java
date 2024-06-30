package com.aluracursos.convert.consumirAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

    public class ConsumirAPI{

        private static final String API_URL = "https://v6.exchangerate-api.com/v6/2dcfee1f62e76501dd92bfb1/latest/USD";
        public static JsonObject fetchRates() throws ApiException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    throw new ApiException("Error al obtener las tasas de cambio: " + response.statusCode());
                }
                return JsonParser.parseString(response.body()).getAsJsonObject();
            } catch (IOException | InterruptedException e) {
                throw new ApiException("Error al conectar con la API", e);
            }
        }
    }


