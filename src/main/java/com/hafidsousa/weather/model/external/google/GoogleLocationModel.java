package com.hafidsousa.weather.model.external.google;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GoogleLocationModel {

    private Double lat;

    private Double lng;

    public GoogleLocationModel() {

    }

    public Double getLat() {

        return lat;
    }

    public void setLat(Double lat) {

        this.lat = lat;
    }

    public Double getLng() {

        return lng;
    }

    public void setLng(Double lng) {

        this.lng = lng;
    }
}
