package com.hafidsousa.weather.service;

import com.hafidsousa.weather.BaseTest;
import com.hafidsousa.weather.model.Profiles;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.model.external.ExternalWeatherModel;
import com.hafidsousa.weather.model.external.google.GoogleResponseModel;
import com.hafidsousa.weather.service.external.IGoogleApiService;
import com.hafidsousa.weather.service.external.IWorldWeatherOnlineApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Hafid Ferreira Sousa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(value = Profiles.MOCK_ENVIRONMENT)
public class WeatherServiceTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceTest.class);

    @Autowired
    private IWeatherService weatherService;

    @MockBean
    private IWorldWeatherOnlineApiService weatherOnlineApiService;

    @MockBean
    private IGoogleApiService googleApiService;

    /**
     * @throws Exception
     */
    @Test
    public void testGetWeather() throws Exception {

        String location = "Sydney";

        given(this.weatherOnlineApiService.getWeather(anyString()))
                .willReturn(this.getMockedExternalWeather(location));

        given(this.googleApiService.getElevation(anyDouble(), anyDouble()))
                .willReturn(this.getMockedExternalElevation(location));

        Mono<WeatherModel> weather = weatherService.getWeather(anyString());

        WeatherModel response = weather.block();

        assert response != null;

        assertEquals(Double.valueOf(56.49151992797852), response.getElevation());
        assertEquals(Double.valueOf(-33.883), response.getLatitude());
        assertEquals(Double.valueOf(151.217), response.getLongitude());
        assertEquals("Sydney", response.getLocation());

        verify(this.weatherOnlineApiService, times(1)).getWeather(anyString());
        verify(this.googleApiService, times(1)).getElevation(anyDouble(), anyDouble());
    }
}
