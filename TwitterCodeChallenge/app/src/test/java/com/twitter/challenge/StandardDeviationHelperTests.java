package com.twitter.challenge;

import com.twitter.challenge.util.StandardDeviationHelper;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.within;

public class StandardDeviationHelperTests {
    @Test
    public void calculateStandardDeviation_shouldReturnCorrectResponse_whenPassedInValidInput() {
        final Offset<Float> precision = within(0.01f);
        final float[] arr = {1, 2, 3, 4, 5};

        assertThat(StandardDeviationHelper.calculateStandardDeviation(arr)).isEqualTo(1.41421f, precision);
    }

    @Test
    public void calculateStandardDeviation_shouldReturnCorrectResponse_whenPassedInSameInputs() {
        final Offset<Float> precision = within(0.01f);
        final float[] arr = {1, 1, 1, 1, 1};

        assertThat(StandardDeviationHelper.calculateStandardDeviation(arr)).isEqualTo(0, precision);
    }

    @Test
    public void calculateStandardDeviation_shouldReturnCorrectResponse_whenPassedInValidInputWithNegatives() {
        final Offset<Float> precision = within(0.01f);
        final float[] arr = {1, 2, 3, 4, -100};

        assertThat(StandardDeviationHelper.calculateStandardDeviation(arr)).isEqualTo(41.01219f, precision);
    }

    @Test
    public void calculateStandardDeviation_shouldReturnCorrectResponse_whenPassedInZeroes() {
        final Offset<Float> precision = within(0.01f);
        final float[] arr = {0, 0, 0, 0};

        assertThat(StandardDeviationHelper.calculateStandardDeviation(arr)).isEqualTo(0, precision);
    }

    @Test
    public void calculateStandardDeviation_shouldReturnCorrectResponse_whenPassedInRangeOfNumbers() {
        final Offset<Float> precision = within(0.01f);
        final float[] arr = {-100, -75, 0, 75, 100};

        assertThat(StandardDeviationHelper.calculateStandardDeviation(arr)).isEqualTo(79.05694f, precision);
    }

    @Test
    public void calculateStandardDeviation_shouldReturnNegativeOne_whenPassedInNull() {
        assertThat(StandardDeviationHelper.calculateStandardDeviation(null)).isEqualTo(-1);
    }
}
