package com.santander.api.weather.controller;

import com.santander.api.weather.dto.WeatherRequestDTO;
import com.santander.api.weather.dto.WeatherResponseDTO;
import com.santander.api.weather.service.WeatherService;
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

    @GetMapping(path = "/weather")
    public ResponseEntity<List<WeatherResponseDTO>> getWeather(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> date,
                                                               @RequestParam(required = false) Optional<String> city,
                                                               @RequestParam(required = false) Optional<String> sort) {
        List<WeatherResponseDTO> weather = weatherService.getWeather(date, city, sort);
        return new ResponseEntity<List<WeatherResponseDTO>>(weather,HttpStatus.OK);
    }

    @GetMapping(path = "/weather/{id}")
    public ResponseEntity<WeatherResponseDTO> getWeatherById(@PathVariable Integer id) {
        WeatherResponseDTO weather = weatherService.getWeatherById(id);
        if (weather != null) {
            return new ResponseEntity<WeatherResponseDTO>(weather, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponseDTO> createWeather(@RequestBody WeatherRequestDTO request) {
        WeatherResponseDTO weather = weatherService.createWeather(request);
        return new ResponseEntity<WeatherResponseDTO>(weather, HttpStatus.CREATED);
    }
}
