package com.hafidsousa.weather.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WeatherModel {

    private String location;

    private Double latitude;

    private Double longitude;

    private Double elevation;

    private List<WeatherConditionsModel> conditions = new ArrayList<>();

    public WeatherModel() {

    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public Double getLatitude() {

        return latitude;
    }

    public void setLatitude(Double latitude) {

        this.latitude = latitude;
    }

    public Double getLongitude() {

        return longitude;
    }

    public void setLongitude(Double longitude) {

        this.longitude = longitude;
    }

    public Double getElevation() {

        return elevation;
    }

    public void setElevation(Double elevation) {

        this.elevation = elevation;
    }

    public List<WeatherConditionsModel> getConditions() {

        return conditions;
    }

    public void setConditions(List<WeatherConditionsModel> conditions) {

        this.conditions = conditions;
    }
}
