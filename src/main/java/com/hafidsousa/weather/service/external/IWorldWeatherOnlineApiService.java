package com.hafidsousa.weather.service.external;

import com.hafidsousa.weather.model.external.ExternalWeatherModel;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */

public interface IWorldWeatherOnlineApiService {

    Mono<ExternalWeatherModel> getWeather(String query);
}
