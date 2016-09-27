package com.twitter.challenge.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twitter.challenge.R;
import com.twitter.challenge.util.TemperatureConverter;

// Implement a custom view for the weather conditions in case in the
// future we want to show the weather conditions for the next n days.
public class WeatherConditionsLayout extends RelativeLayout {

    private TextView temperature;
    private TextView windSpeed;
    private TextView cloudiness;
    private ImageView cloudinessImage;

    public WeatherConditionsLayout(Context context) {
        super(context);
        init();
    }

    public WeatherConditionsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeatherConditionsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setTemperature(float celsiusTemperature) {
        temperature.setText(getContext().getString(R.string.temperature,
                celsiusTemperature, TemperatureConverter.celsiusToFahrenheit(celsiusTemperature)));
    }

    public void setWindSpeed(float wind) {
        windSpeed.setText(getContext().getString(R.string.wind_speed, wind));
    }

    public void setCloudiness(float cloudinessPercent) {
        cloudiness.setText(getContext().getString(R.string.cloudiness, cloudinessPercent));
        cloudinessImage.setVisibility(cloudinessPercent > 50 ? View.VISIBLE: View.GONE);
    }

    private void init() {
        inflate(getContext(), R.layout.weather_conditions, this);

        temperature = (TextView) findViewById(R.id.temperature);
        windSpeed = (TextView) findViewById(R.id.wind_speed);
        cloudiness = (TextView) findViewById(R.id.cloudiness);
        cloudinessImage = (ImageView) findViewById(R.id.cloudiness_image);
    }
}
