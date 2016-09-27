package com.twitter.challenge;

import android.util.Log;

import com.twitter.challenge.model.WeatherInfo;
import com.twitter.challenge.util.WeatherInfoJsonParser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class WeatherInfoJsonParserTests {
    @Test
    public void parse_shouldReturnWeatherInfoObject_whenPassedInValidInput() {
        final String input = "{\"coord\":{\"lon\":-122.42,\"lat\":37.77},\"weather\":{\"temp\":14.77,\"pressure\":1007,\"humidity\":85},\"wind\":{\"speed\":0.51,\"deg\":284},\"rain\":{\"3h\":1},\"clouds\":{\"cloudiness\":65},\"name\":\"San Francisco\"}\"";
        final WeatherInfo info = WeatherInfoJsonParser.parse(input);

        assertThat(info.getTemperature()).isEqualTo(14.77f);
        assertThat(info.getWindSpeed()).isEqualTo(0.51f);
        assertThat(info.getCloudiness()).isEqualTo(65f);

    }

    @Test
    public void parse_shouldReturnNull_whenPassedInInvalidInput() {
        PowerMockito.mockStatic(Log.class);

        // No temperature.
        final String input = "{\"coord\":{\"lon\":-122.42,\"lat\":37.77},\"weather\":{\"pressure\":1007,\"humidity\":85},\"wind\":{\"speed\":0.51,\"deg\":284},\"rain\":{\"3h\":1},\"clouds\":{\"cloudiness\":65},\"name\":\"San Francisco\"}\"";
        final WeatherInfo info = WeatherInfoJsonParser.parse(input);

        assertThat(info).isEqualTo(null);
    }
}
