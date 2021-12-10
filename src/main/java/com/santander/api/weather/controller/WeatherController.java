package com.santander.api.weather.controller;

import com.santander.api.weather.dto.WeatherRequestDTO;
import com.santander.api.weather.dto.WeatherResponseDTO;
import com.santander.api.weather.mapper.WeatherMapper;
import com.santander.api.weather.model.Weather;
import com.santander.api.weather.service.WeatherService;
import com.santander.api.weather.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherMapper weatherMapper;

    @GetMapping(path = "/weather")
    public ResponseEntity<List<WeatherResponseDTO>> getWeather(@RequestParam(required = false) @DateTimeFormat(pattern = Constants.DATE_FORMAT_PATTERN) Optional<Date> date,
                                                               @RequestParam(required = false) Optional<String> city,
                                                               @RequestParam(required = false) Optional<String> sort) {
        List<Weather> weather = weatherService.getWeather(date, city, sort);

        List<WeatherResponseDTO> weatherResponse = weatherMapper.toWeatherResponseDTOs(weather);

        return new ResponseEntity<List<WeatherResponseDTO>>(weatherResponse,HttpStatus.OK);
    }

    @GetMapping(path = "/weather/{id}")
    public ResponseEntity<WeatherResponseDTO> getWeatherById(@PathVariable Integer id) {
        Weather weather = weatherService.getWeatherById(id);
        if (weather != null) {
            WeatherResponseDTO weatherResponse = weatherMapper.toWeatherResponseDTO(weather);
            return new ResponseEntity<WeatherResponseDTO>(weatherResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponseDTO> createWeather(@RequestBody WeatherRequestDTO request) {
        Weather weather = weatherService.createWeather(weatherMapper.toEntity(request));
        WeatherResponseDTO weatherResponse = weatherMapper.toWeatherResponseDTO(weather);
        return new ResponseEntity<WeatherResponseDTO>(weatherResponse, HttpStatus.CREATED);
    }
}