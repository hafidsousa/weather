package com.hafidsousa.weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.model.external.ExternalWeatherModel;
import com.hafidsousa.weather.model.external.google.GoogleResponseModel;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Hafid Ferreira Sousa
 */
public class BaseTest {

    private static final String BASE_DIR = "samples";

    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);

    private ObjectMapper mapper = new ObjectMapper();

    public BaseTest() {

        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    }

    private String getFileContent(String filename) throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        return IOUtils.toString(classLoader.getResourceAsStream(BASE_DIR + File.separator + filename), "UTF-8");

    }

    public GoogleResponseModel getElevationResponse(String location) throws Exception {

        return mapper.readValue(
                getFileContent("google.elevation.response." + location.toLowerCase() + ".json"),
                new TypeReference<GoogleResponseModel>() {
                }
        );
    }

    public ExternalWeatherModel getExternalWeather(String location) throws Exception {

        return mapper.readValue(
                getFileContent("local.weather.response." + location.toLowerCase() + ".json"),
                new TypeReference<ExternalWeatherModel>() {
                }
        );
    }

    public WeatherModel getWeatherModelFromString(String response) throws Exception {

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"));
        return mapper.readValue(
                response,
                new TypeReference<WeatherModel>() {
                }
        );
    }

    public String getExternalWeatherString() throws Exception {

        return getFileContent("local.weather.response.sydney.json");

    }

    public Mono<ExternalWeatherModel> getMockedExternalWeather(String location) throws Exception {

        ExternalWeatherModel externalWeather = getExternalWeather(location);

        return Mono.just(externalWeather);
    }

    public Mono<Double> getMockedExternalElevation(String location) throws Exception {

        GoogleResponseModel elevationResponse = getElevationResponse(location);

        if (!CollectionUtils.isEmpty(elevationResponse.getResults()))
        {
            return Mono.just(elevationResponse.getResults().get(0).getElevation());
        }
        else
        {
            return Mono.empty();
        }
    }
}
