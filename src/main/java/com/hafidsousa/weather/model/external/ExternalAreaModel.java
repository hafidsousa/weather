package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalAreaModel {

    private Double latitude;

    private Double longitude;

    private List<ExternalValueModel> areaName;


    public ExternalAreaModel() {

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

    public List<ExternalValueModel> getAreaName() {

        return areaName;
    }

    public void setAreaName(List<ExternalValueModel> areaName) {

        this.areaName = areaName;
    }
}
