package com.twitter.challenge.callback;

import android.support.annotation.Nullable;

import com.twitter.challenge.WeatherPresenter;
import com.twitter.challenge.model.WeatherInfo;

import java.lang.ref.WeakReference;

public class WeatherInfoConditionsCallback implements WeatherInfoCallback {

    private final WeakReference<WeatherPresenter> presenterReference;

    public WeatherInfoConditionsCallback(WeatherPresenter presenter) {
        presenterReference = new WeakReference<>(presenter);
    }

    @Override
    public void onPreExecute() {
        if (presenterReference.get() != null) {
            presenterReference.get().onCurrentConditionsPreExecute();
        }
    }

    @Override
    public void onPostExecute(@Nullable WeatherInfo[] weatherInfoArray) {
        if (presenterReference.get() != null) {
            presenterReference.get().onCurrentConditionsPostExecute();

            // If the response is a single length array then pass
            // the information back to the presenter.
            if (weatherInfoArray != null && weatherInfoArray.length == 1) {
                final WeatherInfo info = weatherInfoArray[0];
                if (info != null) {
                    presenterReference.get().onCurrentConditionsSuccess(
                            info.getTemperature(),
                            info.getWindSpeed(),
                            info.getCloudiness());
                }
            } else {
                presenterReference.get().onCurrentConditionsFailure();
            }
        }
    }
}
