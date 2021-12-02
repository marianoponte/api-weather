package com.santander.api.weather.service.impl;

import com.santander.api.weather.dto.WeatherRequestDTO;
import com.santander.api.weather.dto.WeatherResponseDTO;
import com.santander.api.weather.model.Weather;
import com.santander.api.weather.repository.WeatherRepository;
import com.santander.api.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<WeatherResponseDTO> getWeather(Optional<Date> date, Optional<String> city, Optional<String> sort) {
        List<Weather> weather = new ArrayList<>();
        if (date.isPresent()) {
            weather = weatherRepository.findByDate(date.get());
        } else if (city.isPresent()) {
            weather = weatherRepository.findByCity(city.get());
        }
        else if (sort.isPresent()) {
            if ("data".equals(sort.get())) {
                weather = weatherRepository.findAllByOrderByDate();
            } else if ("-data".equals(sort.get())) {
                weather = weatherRepository.findAllByOrderByDateDesc();
            }
        } else {
            weather = weatherRepository.findAll();
        }
        return weather.stream().map(this::createFrom)
                .collect(Collectors.toList());
    }

    @Override
    public WeatherResponseDTO getWeatherById(Integer id) {
        WeatherResponseDTO responseDTO = new WeatherResponseDTO();
        Optional<Weather> weather = weatherRepository.findById(id);
        if (weather.isPresent()) {
            return createFrom(weather.get());
        } else return null;
    }

    @Override
    public WeatherResponseDTO createWeather(WeatherRequestDTO weatherRequest) {
        Weather weather = weatherRepository.save(createFrom(weatherRequest));
        WeatherResponseDTO weatherResponseDTO = createFrom(weather);
        return weatherResponseDTO;
    }

    public WeatherResponseDTO createFrom(Weather weather) {
        WeatherResponseDTO dto = new WeatherResponseDTO();
        if (weather != null) {
            dto.setId(weather.getId());
            dto.setDate(weather.getDate());
            dto.setLat(weather.getLat());
            dto.setLon(weather.getLon());
            dto.setCity(weather.getCity());
            dto.setState(weather.getState());
            dto.setTemperatures(weather.getTemperatures());
        }
        return dto;
    }

    public Weather createFrom(WeatherRequestDTO weatherRequest) {
        Weather weather = new Weather();
        if (weatherRequest != null) {
            weather.setDate(weatherRequest.getDate());
            weather.setLat(weatherRequest.getLat());
            weather.setLon(weatherRequest.getLon());
            weather.setCity(weatherRequest.getCity());
            weather.setState(weatherRequest.getState());
            weather.setTemperatures(weatherRequest.getTemperatures());
        }
        return weather;
    }
}
