package com.hafidsousa.weather.service.external;

import com.hafidsousa.weather.config.Utils;
import com.hafidsousa.weather.model.external.ExternalWeatherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */
@Service("worldWeatherOnlineApiService")
public class WorldWeatherOnlineApiService implements IWorldWeatherOnlineApiService {

    private static final Logger LOG = LoggerFactory.getLogger(WorldWeatherOnlineApiService.class);

    @Value("${local.weather.api.base.url}")
    private String BASE_URL;

    @Value("${local.weather.api.key}")
    private String KEY;

    public WorldWeatherOnlineApiService() {

    }

    @Override
    public Mono<ExternalWeatherModel> getWeather(String query) {

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
                // Add query parameter
                .queryParam(Utils.params.key, KEY)
                .queryParam(Utils.params.query, query)
                .queryParam(Utils.params.format, "json")
                .queryParam(Utils.params.numOfDays, 1)
                .queryParam(Utils.params.includeLocation, "yes")
                .queryParam(Utils.params.showLocaltime, "yes");

        return WebClient.create()
                .get().uri(builder.buildAndExpand().toUri())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(ExternalWeatherModel.class))
                .doOnError(throwable -> LOG.error(Utils.error.failed_get_url, BASE_URL));
    }
}
