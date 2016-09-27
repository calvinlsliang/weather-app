package com.twitter.challenge;

public interface WeatherView {
    void updateCurrentConditions(float temperature, float windSpeed, float cloudiness);

    void showCurrentConditionsProgress();

    void hideCurrentConditionsProgress();

    void showCurrentConditions();

    void hideCurrentConditions();

    void showNoCurrentConditions();

    void hideNoCurrentConditions();

    void showStdevProgress();

    void hideStdevProgress();

    void showStdev();

    void hideStdev();

    void showNoStdev();

    void hideNoStdev();

    void updateStdev(float stdev);
}
