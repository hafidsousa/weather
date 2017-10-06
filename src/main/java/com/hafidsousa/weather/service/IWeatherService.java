package com.hafidsousa.weather.service;

import com.hafidsousa.weather.model.WeatherModel;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */
public interface IWeatherService {

    Mono<WeatherModel> getWeather(String query);

}
