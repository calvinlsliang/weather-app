package com.twitter.challenge.callback;

import com.twitter.challenge.model.WeatherInfo;

public interface WeatherInfoCallback {

    void onPreExecute();

    void onPostExecute(WeatherInfo[] weatherInfoArray);

}
