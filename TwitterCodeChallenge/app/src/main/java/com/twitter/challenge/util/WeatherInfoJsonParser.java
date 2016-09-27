package com.twitter.challenge.util;

import android.util.Log;

import com.twitter.challenge.model.WeatherInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherInfoJsonParser {

    private static final String WEATHER = "weather";
    private static final String TEMPERATURE = "temp";
    private static final String WIND = "wind";
    private static final String SPEED = "speed";
    private static final String CLOUDS = "clouds";
    private static final String CLOUDINESS = "cloudiness";

    /**
     * Parses the String returned from the weather API call
     * Looks for:
     * {
     *     weather.temp,
     *     wind.speed,
     *     clouds.cloudiness
     * }
     *
     * @param apiResponse The API response from the weather API call.
     * @return WeatherInfo object with the temperature, wind speed, and cloudiness.
     */
    public static WeatherInfo parse(String apiResponse) {
        try {
            JSONObject responseObject = new JSONObject(apiResponse);
            JSONObject weatherObject = responseObject.getJSONObject(WEATHER);
            float temperature = (float) weatherObject.getDouble(TEMPERATURE);

            JSONObject windObject = responseObject.getJSONObject(WIND);
            float windSpeed = (float) windObject.getDouble(SPEED);

            JSONObject cloudsObject = responseObject.getJSONObject(CLOUDS);
            float cloudiness = (float) cloudsObject.getDouble(CLOUDINESS);

            return new WeatherInfo(temperature, windSpeed, cloudiness);
        } catch (JSONException e) {
            Log.e("JsonParseException", e.toString());
        }
        return null;
    }
}
