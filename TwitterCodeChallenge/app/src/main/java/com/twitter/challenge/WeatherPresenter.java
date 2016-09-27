package com.twitter.challenge;

import android.os.Bundle;

import com.twitter.challenge.callback.WeatherInfoConditionsCallback;
import com.twitter.challenge.callback.WeatherInfoStdevCallback;

public class WeatherPresenter {

    private static final String TEMPERATURE = "TEMPERATURE";
    private static final String WIND_SPEED = "WIND_SPEED";
    private static final String CLOUDINESS = "CLOUDINESS";
    private static final String STDEV = "STDEV";
    private static final String VALID_CONDITIONS = "VALID_CONDITIONS";
    private static final String VALID_STDEV = "VALID_STDEV";

    private WeatherView view;
    private WeatherInfoFragment weatherInfoFragment;
    private WeatherInfoConditionsCallback weatherInfoConditionsCallback;
    private WeatherInfoStdevCallback weatherInfoStdevCallback;

    private Boolean validConditions = null;
    private Boolean validStdev = null;
    private float temperature;
    private float windSpeed;
    private float cloudiness;
    private float stdev;

    public void onAttach(WeatherView view) {
        this.view = view;
    }

    public void setWeatherInfoFragment(WeatherInfoFragment weatherInfoFragment) {
        this.weatherInfoFragment = weatherInfoFragment;

        weatherInfoConditionsCallback = new WeatherInfoConditionsCallback(this);
        weatherInfoStdevCallback = new WeatherInfoStdevCallback(this);

        this.weatherInfoFragment.setWeatherInfoConditionsCallback(weatherInfoConditionsCallback);
        this.weatherInfoFragment.setWeatherInfoStdevCallback(weatherInfoStdevCallback);
    }

    public void onCreate(Bundle savedInstanceState) {
        // Check potential saved current conditions
        if (savedInstanceState != null && savedInstanceState.containsKey(VALID_CONDITIONS)) {
            validConditions = savedInstanceState.getBoolean(VALID_CONDITIONS);
            view.hideCurrentConditionsProgress();

            if (validConditions && savedInstanceState.containsKey(TEMPERATURE)
                    && savedInstanceState.containsKey(WIND_SPEED)
                    && savedInstanceState.containsKey(CLOUDINESS)) {
                temperature = savedInstanceState.getFloat(TEMPERATURE);
                windSpeed = savedInstanceState.getFloat(WIND_SPEED);
                cloudiness = savedInstanceState.getFloat(CLOUDINESS);

                view.hideNoCurrentConditions();
                view.showCurrentConditions();
                view.updateCurrentConditions(temperature, windSpeed, cloudiness);
            } else {
                view.hideCurrentConditions();
                view.showNoCurrentConditions();
            }
        } else {
            initCurrentConditions();
        }

        // Check potential saved stdev
        if (savedInstanceState != null && savedInstanceState.containsKey(VALID_STDEV)) {
            validStdev = savedInstanceState.getBoolean(VALID_STDEV);
            view.hideStdevProgress();

            if (validStdev && savedInstanceState.containsKey(STDEV)) {
                stdev = savedInstanceState.getFloat(STDEV);
                view.hideNoStdev();
                view.showStdev();
                view.updateStdev(stdev);
            } else {
                view.hideStdev();
                view.showNoStdev();
            }
        }
    }

    public void onDestroy() {
        view = null;
        weatherInfoConditionsCallback = null;
        weatherInfoStdevCallback = null;
    }

    public void onSaveInstanceState(Bundle outState) {
        if (validConditions != null) {
            if (validConditions) {
                outState.putFloat(TEMPERATURE, temperature);
                outState.putFloat(WIND_SPEED, windSpeed);
                outState.putFloat(CLOUDINESS, cloudiness);
            }

            outState.putBoolean(VALID_CONDITIONS, validConditions);
        }

        if (validStdev != null) {
            if (validStdev) {
                outState.putFloat(STDEV, stdev);
            }

            outState.putBoolean(VALID_STDEV, validStdev);
        }
    }

    public void onStdevButtonClick() {
        weatherInfoFragment.startWeatherInfoStdevTask();
    }

    public void onCurrentConditionsPreExecute() {
        if (view != null) {
            view.hideCurrentConditions();
            view.hideNoCurrentConditions();
            view.showCurrentConditionsProgress();
        }
    }

    public void onCurrentConditionsPostExecute() {
        if (view != null) {
            view.hideCurrentConditionsProgress();
        }
    }

    public void onCurrentConditionsSuccess(float temperature, float windSpeed, float cloudiness) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
        this.validConditions = true;

        if (view != null) {
            view.hideNoCurrentConditions();
            view.showCurrentConditions();
            view.updateCurrentConditions(temperature, windSpeed, cloudiness);
        }
    }

    public void onCurrentConditionsFailure() {
        this.validConditions = false;

        if (view != null) {
            view.hideCurrentConditions();
            view.showNoCurrentConditions();
        }
    }

    public void onStdevPreExecute() {
        if (view != null) {
            view.hideStdev();
            view.hideNoStdev();
            view.showStdevProgress();
        }
    }

    public void onStdevPostExecute() {
        if (view != null) {
            view.hideStdevProgress();
        }
    }

    public void onStdevSuccess(float stdev) {
        this.stdev = stdev;
        this.validStdev = true;

        if (view != null) {
            view.hideNoStdev();
            view.showStdev();
            view.updateStdev(stdev);
        }
    }

    public void onStdevFailure() {
        this.validStdev = false;

        if (view != null) {
            view.hideStdev();
            view.showNoStdev();
        }
    }

    private void initCurrentConditions() {
        weatherInfoFragment.startWeatherInfoConditionsTask();
    }
}
