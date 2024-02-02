package com.example.demo13dz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class APIConnection {

    String apiKey = "36245c78af5309a3dd3cc1d532eedfe1";
    private final String urlString;
    public APIConnection(String urlString) {
        this.urlString = urlString;
    }

    try {
        // Ваш код запроса к API OpenWeatherMap

        // Формирование URL
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid={36245c78af5309a3dd3cc1d532eedfe1}";
        URL url = null;
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Создание объекта HttpURLConnection
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        // Получение ответа от сервера
        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Получение данных и обработка ответа
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response from OpenWeatherMap API:");
                System.out.println(response.toString());
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                // Закрытие соединения
                connection.disconnect();
            }
        }
    } catch(IOException e) {
        e.printStackTrace();
    }


}




