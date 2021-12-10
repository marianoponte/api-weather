package com.santander.api.weather.repository;

import com.santander.api.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeatherRepository extends JpaRepository<Weather, Integer>, JpaSpecificationExecutor<Weather> {

}
