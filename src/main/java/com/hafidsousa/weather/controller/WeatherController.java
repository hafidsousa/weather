package com.hafidsousa.weather.controller;

import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.service.IWeatherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */
@RestController
@RequestMapping("v1")
public class WeatherController {

    private IWeatherService weatherService;

    public WeatherController(IWeatherService weatherService) {

        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WeatherModel> getWeather(
            @RequestParam(value = "q", required = false) String query
    ) {

        return weatherService.getWeather(query);
    }
}
