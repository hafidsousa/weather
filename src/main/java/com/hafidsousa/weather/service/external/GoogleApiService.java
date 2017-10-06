package com.hafidsousa.weather.service.external;

import com.hafidsousa.weather.config.Utils;
import com.hafidsousa.weather.model.external.google.GoogleResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */
@Service("googleApiService")
public class GoogleApiService implements IGoogleApiService {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleApiService.class);

    @Value("${google.weather.api.base.url}")
    private String BASE_URL;

    @Value("${google.weather.api.key}")
    private String KEY;

    public GoogleApiService() {

    }

    @Override
    public Mono<Double> getElevation(Double lat, Double lgt) {

        // Query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
                // Add query parameter
                .queryParam(Utils.params.key, KEY)
                .queryParam(Utils.params.locations, String.format("%s,%s", lat, lgt));

        return WebClient.create()
                .get().uri(builder.buildAndExpand().toUri())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(GoogleResponseModel.class))
                .map(googleResponseModel -> {
                    if (!CollectionUtils.isEmpty(googleResponseModel.getResults()))
                    {
                        return googleResponseModel.getResults().get(0).getElevation();
                    }
                    return 0.0;
                })
                .doOnError(throwable -> LOG.error(Utils.error.failed_get_url, BASE_URL));
    }
}
