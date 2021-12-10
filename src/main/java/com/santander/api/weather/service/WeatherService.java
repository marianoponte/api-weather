package com.santander.api.weather.service;

import com.santander.api.weather.model.Weather;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherService {

    List<Weather> getWeather(Optional<Date> date, Optional<String> city, Optional<String> sort);

    Weather getWeatherById(Integer id);

    Weather createWeather(Weather weather);

}
