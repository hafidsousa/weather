package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalWeatherModel {

    private ExternalResponseModel data;

    public ExternalWeatherModel() {

    }

    public ExternalResponseModel getData() {

        return data;
    }

    public void setData(ExternalResponseModel data) {

        this.data = data;
    }
}
