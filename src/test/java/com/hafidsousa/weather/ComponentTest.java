package com.hafidsousa.weather;

import com.hafidsousa.weather.controller.WeatherController;
import com.hafidsousa.weather.model.Profiles;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.service.external.IGoogleApiService;
import com.hafidsousa.weather.service.external.IWorldWeatherOnlineApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author Hafid Ferreira Sousa
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles(value = Profiles.MOCK_ENVIRONMENT)
public class ComponentTest extends BaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ComponentTest.class);

    @Autowired
    private WeatherController weatherController;

    private WebTestClient webTestClient;

    @MockBean
    private IWorldWeatherOnlineApiService weatherOnlineApiService;

    @MockBean
    private IGoogleApiService googleApiService;

    @Before
    public void setUp() throws Exception {

        webTestClient = WebTestClient.bindToController(weatherController).build();

    }

    @Test
    public void testComponent() throws Exception {

        Stream<String> locations = Stream.of("Sydney", "London", "Dubai", "Melbourne", "Santiago");

        locations.forEach(
                (location) -> {
                    try
                    {
                        given(this.weatherOnlineApiService.getWeather(anyString()))
                                .willReturn(this.getMockedExternalWeather(location));

                        given(this.googleApiService.getElevation(anyDouble(), anyDouble()))
                                .willReturn(this.getMockedExternalElevation(location));

                        EntityExchangeResult<byte[]> result = webTestClient.get().uri("/v1/weather?q=" + location)
                                .accept(MediaType.APPLICATION_JSON)
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .returnResult();

                        assert result.getResponseBody() != null;

                        WeatherModel weatherModel = getWeatherModelFromString(new String(result.getResponseBody()));

                        String print = String.format(
                                "%s|%s,%s,%s|%s|%s|%s|%s|%s",
                                weatherModel.getLocation(),
                                weatherModel.getLatitude(),
                                weatherModel.getLongitude(),
                                weatherModel.getElevation(),
                                weatherModel.getConditions().get(0).getLocalTime(),
                                weatherModel.getConditions().get(0).getConditions(),
                                weatherModel.getConditions().get(0).getTemperature(),
                                weatherModel.getConditions().get(0).getPressure(),
                                weatherModel.getConditions().get(0).getHumidity()

                        );

                        // Print output

                        LOG.info(print);
                    }
                    catch (Exception e)
                    {
                        LOG.error(e.getMessage());
                    }
                }
        );
    }
}
