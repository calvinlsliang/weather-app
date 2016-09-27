package com.twitter.challenge;

import android.app.Fragment;
import android.os.Bundle;

import com.twitter.challenge.callback.WeatherInfoConditionsCallback;
import com.twitter.challenge.callback.WeatherInfoStdevCallback;

public class WeatherInfoFragment extends Fragment {

    private WeatherInfoConditionsCallback weatherInfoConditionsCallback;
    private WeatherInfoStdevCallback weatherInfoStdevCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        weatherInfoConditionsCallback = null;
        weatherInfoStdevCallback = null;
    }

    public void setWeatherInfoConditionsCallback(WeatherInfoConditionsCallback weatherInfoConditionsCallback) {
        this.weatherInfoConditionsCallback = weatherInfoConditionsCallback;
    }

    public void setWeatherInfoStdevCallback(WeatherInfoStdevCallback weatherInfoStdevCallback) {
        this.weatherInfoStdevCallback = weatherInfoStdevCallback;
    }

    public void startWeatherInfoConditionsTask() {
        new WeatherInfoConditionsAsyncTask(weatherInfoConditionsCallback).execute();
    }

    public void startWeatherInfoStdevTask() {
        new WeatherInfoStdevAsyncTask(weatherInfoStdevCallback).execute();
    }
}

