package com.santander.api.weather.service.impl;

import com.santander.api.weather.model.Weather;
import com.santander.api.weather.repository.WeatherRepository;
import com.santander.api.weather.service.WeatherService;
import com.santander.api.weather.specifications.WeatherSpecification;
import com.santander.api.weather.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<Weather> getWeather(Optional<Date> date, Optional<String> city, Optional<String> sort) {
        List<Weather> weather = new ArrayList<>();
        Map<String, Object> filters = new HashMap<>();

        if (date.isPresent()) {
            filters.put("date", date.get());
        }
        if (city.isPresent()) {
            filters.put("city", city.get());
        }
        if (sort.isPresent() && Constants.SORT_DATE_ASC.equals(sort.get())) {
            weather = weatherRepository.findAll(WeatherSpecification.findWeatherByFilters(filters), Sort.by("date").ascending());
        } else if (sort.isPresent() && Constants.SORT_DATE_DESC.equals(sort.get())){
            weather = weatherRepository.findAll(WeatherSpecification.findWeatherByFilters(filters), Sort.by("date").descending());
        } else {
            weather = weatherRepository.findAll(WeatherSpecification.findWeatherByFilters(filters));
        }
        return weather;
    }

    @Override
    public Weather getWeatherById(Integer id) {
        Optional<Weather> weather = weatherRepository.findById(id);
        if (weather.isPresent()) {
            return weather.get();
        } else return null;
    }

    @Override
    public Weather createWeather(Weather weatherRequest) {
        Weather weather = weatherRepository.save(weatherRequest);
        return weather;
    }
}