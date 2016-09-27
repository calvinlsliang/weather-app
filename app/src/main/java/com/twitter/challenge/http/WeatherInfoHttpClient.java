package com.twitter.challenge.http;

import com.twitter.challenge.model.WeatherInfo;
import com.twitter.challenge.util.WeatherInfoJsonParser;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherInfoHttpClient {

    private final static String URL = "https://dl.dropboxusercontent.com/u/820447/weather/";
    private final static String CURRENT_CONDITIONS_ENDPOINT = "current.json";
    private final static String FUTURE_ENDPOINT = "future_%1d.json";

    private final static int FUTURE_N_DAYS = 5;

    private final static OkHttpClient httpClient = new OkHttpClient();

    public static WeatherInfo[] getWeatherInfoConditions() throws IOException {
        final Request request = new Request.Builder()
                .url(URL + CURRENT_CONDITIONS_ENDPOINT)
                .build();

        final Response response = httpClient.newCall(request).execute();
        final String body = response.body().string();
        return new WeatherInfo[] { WeatherInfoJsonParser.parse(body) };
    }

    public static WeatherInfo[] getWeatherInfoStdev() throws IOException {
        final WeatherInfo[] weatherInfoArray = new WeatherInfo[FUTURE_N_DAYS];

        // Create an nth length array, one length for every day
        // we want to check in the future. Start the index at i = 1
        // because future_0 would just be the current day.
        for (int i = 1; i <= FUTURE_N_DAYS; i++) {
            weatherInfoArray[i-1] = getWeatherInfoFutureN(i);
        }
        return weatherInfoArray;
    }

    private static WeatherInfo getWeatherInfoFutureN(int nthDay) throws IOException {
        final Request request = new Request.Builder()
                .url(URL + String.format(FUTURE_ENDPOINT, nthDay))
                .build();

        final Response response = httpClient.newCall(request).execute();
        final String body = response.body().string();
        return WeatherInfoJsonParser.parse(body);
    }
}
