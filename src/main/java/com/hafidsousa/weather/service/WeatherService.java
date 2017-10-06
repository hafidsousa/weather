package com.hafidsousa.weather.service;

import com.hafidsousa.weather.config.Utils;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.service.external.IGoogleApiService;
import com.hafidsousa.weather.service.external.IWorldWeatherOnlineApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */
@Service("weatherService")
public class WeatherService implements IWeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherService.class);

    private IWorldWeatherOnlineApiService weatherOnlineApiService;

    private IGoogleApiService googleApiService;

    public WeatherService(
            IWorldWeatherOnlineApiService weatherOnlineApiService,
            IGoogleApiService googleApiService
    ) {

        this.weatherOnlineApiService = weatherOnlineApiService;
        this.googleApiService = googleApiService;
    }

    @Override
    public Mono<WeatherModel> getWeather(String query) {

        return weatherOnlineApiService.getWeather(query).map(
                Utils::convertToInternalModel
        ).flatMap(
                result -> googleApiService.getElevation(result.getLatitude(), result.getLongitude())
                        .map(
                                elevation -> {
                                    result.setElevation(elevation);
                                    return result;
                                }
                        )
        );
    }
}
