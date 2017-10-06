package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalValueModel {

    private String value;

    public ExternalValueModel() {

    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }
}
