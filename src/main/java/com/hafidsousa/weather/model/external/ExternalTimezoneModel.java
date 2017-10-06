package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalTimezoneModel {

    @JsonProperty("localtime")
    private String localTime;


    public ExternalTimezoneModel() {

    }

    public String getLocalTime() {

        return localTime;
    }

    public void setLocalTime(String localTime) {

        this.localTime = localTime;
    }
}
