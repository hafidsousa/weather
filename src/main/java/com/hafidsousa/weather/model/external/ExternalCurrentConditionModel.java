package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalCurrentConditionModel {

    @JsonProperty("observation_time")
    private String observationTime;

    @JsonProperty("temp_C")
    private String temperatureCelsius;

    private List<ExternalValueModel> weatherDesc;

    private String humidity;

    private String pressure;


    public ExternalCurrentConditionModel() {

    }

    public String getObservationTime() {

        return observationTime;
    }

    public void setObservationTime(String observationTime) {

        this.observationTime = observationTime;
    }

    public String getTemperatureCelsius() {

        return temperatureCelsius;
    }

    public void setTemperatureCelsius(String temperatureCelsius) {

        this.temperatureCelsius = temperatureCelsius;
    }

    public List<ExternalValueModel> getWeatherDesc() {

        return weatherDesc;
    }

    public void setWeatherDesc(List<ExternalValueModel> weatherDesc) {

        this.weatherDesc = weatherDesc;
    }

    public String getHumidity() {

        return humidity;
    }

    public void setHumidity(String humidity) {

        this.humidity = humidity;
    }

    public String getPressure() {

        return pressure;
    }

    public void setPressure(String pressure) {

        this.pressure = pressure;
    }
}
