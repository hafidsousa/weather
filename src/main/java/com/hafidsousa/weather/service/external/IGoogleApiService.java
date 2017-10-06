package com.hafidsousa.weather.service.external;

import reactor.core.publisher.Mono;

/**
 * @author Hafid Ferreira Sousa
 */

public interface IGoogleApiService {

    Mono<Double> getElevation(Double lat, Double lgt);
}
