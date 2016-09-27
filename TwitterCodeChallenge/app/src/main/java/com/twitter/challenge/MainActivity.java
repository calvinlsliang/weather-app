package com.twitter.challenge;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.twitter.challenge.view.WeatherConditionsLayout;

public class MainActivity extends AppCompatActivity implements WeatherView {

    private static final String WEATHER_INFO_FRAGMENT_TAG = "WEATHER_INFO_FRAGMENT_TAG";

    private ProgressBar conditionsProgress;
    private WeatherConditionsLayout currentConditions;
    private TextView noCurrentConditions;
    private ProgressBar stdevProgress;
    private TextView stdev;
    private TextView noStdev;

    private final WeatherPresenter presenter = new WeatherPresenter();

    private WeatherInfoFragment weatherInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conditionsProgress = (ProgressBar) findViewById(R.id.conditions_progress);
        currentConditions = (WeatherConditionsLayout) findViewById(R.id.current_conditions);
        noCurrentConditions = (TextView) findViewById(R.id.no_current_conditions);
        stdevProgress = (ProgressBar) findViewById(R.id.stdev_progress);
        stdev = (TextView) findViewById(R.id.stdev);
        noStdev = (TextView) findViewById(R.id.no_stdev);

        initWeatherInfoFragment();
        initStdevListener();

        presenter.onAttach(this);
        presenter.setWeatherInfoFragment(weatherInfoFragment);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showCurrentConditionsProgress() {
        conditionsProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCurrentConditionsProgress() {
        conditionsProgress.setVisibility(View.GONE);
    }

    @Override
    public void updateCurrentConditions(float temperature, float windSpeed, float cloudiness) {
        currentConditions.setTemperature(temperature);
        currentConditions.setWindSpeed(windSpeed);
        currentConditions.setCloudiness(cloudiness);
    }

    @Override
    public void showCurrentConditions() {
        currentConditions.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCurrentConditions() {
        currentConditions.setVisibility(View.GONE);
    }

    @Override
    public void showNoCurrentConditions() {
        noCurrentConditions.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoCurrentConditions() {
        noCurrentConditions.setVisibility(View.GONE);
    }

    @Override
    public void updateStdev(float value) {
        stdev.setText(String.format(getString(R.string.stdev), value));
    }

    @Override
    public void showStdevProgress() {
        stdevProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideStdevProgress() {
        stdevProgress.setVisibility(View.GONE);
    }

    @Override
    public void showStdev() {
        stdev.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideStdev() {
        stdev.setVisibility(View.GONE);
    }

    @Override
    public void showNoStdev() {
        noStdev.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoStdev() {
        noStdev.setVisibility(View.GONE);
    }

    // Create a fragment for async tasks so even on screen rotation the task will still
    // be kept alive and can make the appropriate UI changes when it finishes.
    private void initWeatherInfoFragment() {
        FragmentManager manager = getFragmentManager();
        weatherInfoFragment = (WeatherInfoFragment) manager.findFragmentByTag(WEATHER_INFO_FRAGMENT_TAG);
        if (weatherInfoFragment == null) {
            weatherInfoFragment = new WeatherInfoFragment();
            manager.beginTransaction().add(weatherInfoFragment, WEATHER_INFO_FRAGMENT_TAG).commit();
        }
    }

    private void initStdevListener() {
        Button stdevButton = (Button) findViewById(R.id.stdev_button);
        stdevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onStdevButtonClick();
            }
        });
    }
}
