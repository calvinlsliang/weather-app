package com.twitter.challenge.util;

public class StandardDeviationHelper {

    /**
     * Calculates the standard deviation.
     *
     * @param array Array of float values.
     * @return Standard deviation calculated from the array of floats.
     */
    public static float calculateStandardDeviation(float[] array) {
        if (array == null) {
            return -1;
        }

        // Find mean
        final float mean = calculateMean(array);

        // Find summation of of (array[n] - mean) ^ 2
        final float distance = calculateDistanceToMean(array, mean);

        // Divide by number of elements
        final float quotient = distance / array.length;

        // Square root
        return (float) Math.sqrt(quotient);
    }

    private static float calculateMean(float[] array) {
        float sum = 0;
        for (float value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    private static float calculateDistanceToMean(float[] array, float mean) {
        float distance = 0;
        for (float value : array) {
            distance += Math.pow(value - mean, 2);
        }
        return distance;
    }
}
