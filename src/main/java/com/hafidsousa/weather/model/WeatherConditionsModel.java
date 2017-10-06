package com.hafidsousa.weather.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WeatherConditionsModel {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime localTime;

    private String conditions;

    private Double temperature;

    private Double pressure;

    private Double humidity;

    public WeatherConditionsModel() {

    }

    public LocalDateTime getLocalTime() {

        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {

        this.localTime = localTime;
    }

    public String getConditions() {

        return conditions;
    }

    public void setConditions(String conditions) {

        this.conditions = conditions;
    }

    public Double getTemperature() {

        return temperature;
    }

    public void setTemperature(Double temperature) {

        this.temperature = temperature;
    }

    public Double getPressure() {

        return pressure;
    }

    public void setPressure(Double pressure) {

        this.pressure = pressure;
    }

    public Double getHumidity() {

        return humidity;
    }

    public void setHumidity(Double humidity) {

        this.humidity = humidity;
    }
}
