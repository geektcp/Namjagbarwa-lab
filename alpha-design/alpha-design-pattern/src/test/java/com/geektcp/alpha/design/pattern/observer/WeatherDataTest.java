package com.geektcp.alpha.design.pattern.observer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class WeatherDataTest {

    @Test
    public void main() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(0, 0, 0);
        weatherData.setMeasurements(1, 1, 1);
        Assert.assertTrue(true);
    }
}
