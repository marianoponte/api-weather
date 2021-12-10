package com.santander.api.weather.mapper;

import com.santander.api.weather.dto.WeatherRequestDTO;
import com.santander.api.weather.dto.WeatherResponseDTO;
import com.santander.api.weather.model.Weather;
import com.santander.api.weather.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherRequestDTO toWeatherRequestDTO(Weather weather);

    @Mapping(target = "date", dateFormat = Constants.DATE_FORMAT_PATTERN)
    WeatherResponseDTO toWeatherResponseDTO(Weather weather);

    List<WeatherResponseDTO> toWeatherResponseDTOs(List<Weather> weather);

    Weather toEntity(WeatherRequestDTO weatherRequestDTO);

}
