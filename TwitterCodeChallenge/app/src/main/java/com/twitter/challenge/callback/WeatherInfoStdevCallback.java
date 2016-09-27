package com.twitter.challenge.callback;

import android.support.annotation.Nullable;

import com.twitter.challenge.WeatherPresenter;
import com.twitter.challenge.model.WeatherInfo;
import com.twitter.challenge.util.StandardDeviationHelper;

import java.lang.ref.WeakReference;

public class WeatherInfoStdevCallback implements WeatherInfoCallback {

    private final WeakReference<WeatherPresenter> presenterReference;

    public WeatherInfoStdevCallback(WeatherPresenter presenter) {
        presenterReference = new WeakReference<>(presenter);
    }

    @Override
    public void onPreExecute() {
        if (presenterReference.get() != null) {
            presenterReference.get().onStdevPreExecute();
        }
    }

    @Override
    public void onPostExecute(@Nullable WeatherInfo[] weatherInfoArray) {
        if (presenterReference.get() != null) {
            presenterReference.get().onStdevPostExecute();

            // If the array is not null, then create a float array from the
            // temperatures from each WeatherInfo and then calculate the
            // standard deviation.
            if (weatherInfoArray != null) {
                final float[] temperatureArray = new float[weatherInfoArray.length];
                for (int i = 0; i < weatherInfoArray.length; i++) {
                    if (weatherInfoArray[i] != null) {
                        temperatureArray[i] = weatherInfoArray[i].getTemperature();
                    }
                }

                final float stdev = StandardDeviationHelper.calculateStandardDeviation(temperatureArray);

                presenterReference.get().onStdevSuccess(stdev);
            } else {
                presenterReference.get().onStdevFailure();
            }
        }
    }
}
