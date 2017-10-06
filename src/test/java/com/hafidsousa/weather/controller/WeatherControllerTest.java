package com.hafidsousa.weather.controller;

import com.hafidsousa.weather.model.Profiles;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.service.IWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author Hafid Ferreira Sousa
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(value = {Profiles.MOCK_ENVIRONMENT})
public class WeatherControllerTest {

    @Autowired
    private WeatherController weatherController;

    @MockBean
    private IWeatherService weatherService;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {

        webTestClient = WebTestClient.bindToController(weatherController).build();
    }

    @Test
    public void testGetWeather() throws Exception {

        given(this.weatherService.getWeather(anyString()))
                .willReturn(this.getWeather());

        webTestClient.get().uri("/v1/weather?q=Sydney")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.elevation").isEqualTo(1.0)
                .jsonPath("$.latitude").isEqualTo(1.1)
                .jsonPath("$.longitude").isEqualTo(1.2);
    }

    public Mono<WeatherModel> getWeather() {

        WeatherModel weatherModel = new WeatherModel();
        weatherModel.setElevation(1.0);
        weatherModel.setLatitude(1.1);
        weatherModel.setLongitude(1.2);

        return Mono.just(weatherModel);
    }
}
