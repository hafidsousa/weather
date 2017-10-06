package com.hafidsousa.weather.model.external.google;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GoogleElevationModel {

    private GoogleLocationModel location;

    private Double elevation;

    public GoogleElevationModel() {

    }

    public GoogleLocationModel getLocation() {

        return location;
    }

    public void setLocation(GoogleLocationModel location) {

        this.location = location;
    }

    public Double getElevation() {

        return elevation;
    }

    public void setElevation(Double elevation) {

        this.elevation = elevation;
    }
}
