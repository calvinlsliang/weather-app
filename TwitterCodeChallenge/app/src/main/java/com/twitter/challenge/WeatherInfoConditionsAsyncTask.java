package com.twitter.challenge;

import android.os.AsyncTask;
import android.util.Log;

import com.twitter.challenge.callback.WeatherInfoCallback;
import com.twitter.challenge.http.WeatherInfoHttpClient;
import com.twitter.challenge.model.WeatherInfo;

import java.io.IOException;

public class WeatherInfoConditionsAsyncTask extends AsyncTask<Void, Void, WeatherInfo[]> {

    private final WeatherInfoCallback weatherInfoCallback;

    public WeatherInfoConditionsAsyncTask(WeatherInfoCallback weatherInfoCallback) {
        this.weatherInfoCallback = weatherInfoCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (weatherInfoCallback != null) {
            weatherInfoCallback.onPreExecute();
        }
    }

    @Override
    protected WeatherInfo[] doInBackground(Void... params) {
        try {
            return WeatherInfoHttpClient.getWeatherInfoConditions();
        } catch (IOException e) {
            Log.e("getWeatherException", e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(WeatherInfo[] result) {
        super.onPostExecute(result);
        if (weatherInfoCallback != null) {
            weatherInfoCallback.onPostExecute(result);
        }
    }
}
