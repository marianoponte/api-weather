package com.santander.api.weather.repository;

import com.santander.api.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    List<Weather> findByDate(Date date);

    List<Weather> findByCity(String city);

    List<Weather> findAllByOrderByDate();

    List<Weather> findAllByOrderByDateDesc();
}
