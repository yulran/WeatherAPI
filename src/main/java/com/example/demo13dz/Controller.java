package com.example.demo13dz;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.MalformedURLException;


public class Controller {

    @FXML
    private TextField CityInput;

    @FXML
    private Text ResponseText;
    private final String WeatherApi = "https://openweathermap.org/api";
    private final String CityApi = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid={36245c78af5309a3dd3cc1d532eedfe1}";

    void getWeatherData(ActionEvent event) throws MalformedURLException { //при нажатии кнопки выдает ответ??
        JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        ResponseText.setText("Current temperature: " + ((JsonObject) todaysWeather).get("the_temp"));
    }

    @FXML
    public String getWoeid() {
        APIConnection apiConnectorCity = new APIConnection(CityApi);
        String city = CityInput.getText();
        JsonObject jsonData = (JsonObject) apiConnectorCity.getJSONArray(city).get(0);
        return jsonData.get("woeid").getAsString();
    }

    public JsonObject GetTodaysWeatherInformation(String woeid) {
        APIConnection apiConnectorWeather = new APIConnection(WeatherApi);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JsonArray weatherArray = weatherJSONObject.getJsonArray("consolidated_weather");

        return weatherArray.get(0).getAsJsonObject();
    }
}
