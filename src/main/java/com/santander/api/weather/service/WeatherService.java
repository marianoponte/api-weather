package com.santander.api.weather.service;
;
import com.santander.api.weather.dto.WeatherRequestDTO;
import com.santander.api.weather.dto.WeatherResponseDTO;
import com.santander.api.weather.model.Weather;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeatherService {

    List<WeatherResponseDTO> getWeather(Optional<Date> date, Optional<String> city, Optional<String> sort);

    WeatherResponseDTO getWeatherById(Integer id);

    WeatherResponseDTO createFrom(Weather weather);

    WeatherResponseDTO createWeather(WeatherRequestDTO weatherRequest);

    Weather createFrom(WeatherRequestDTO weatherRequest);
}
