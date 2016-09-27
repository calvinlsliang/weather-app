package com.twitter.challenge.model;

public class WeatherInfo {
    private final float temperature;
    private final float windSpeed;
    private final float cloudiness;

    public WeatherInfo(float temperature, float windSpeed, float cloudiness) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
    }

    public float getCloudiness() {
        return cloudiness;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }
}
