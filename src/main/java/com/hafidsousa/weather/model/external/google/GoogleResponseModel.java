package com.hafidsousa.weather.model.external.google;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GoogleResponseModel {

    private List<GoogleElevationModel> results;

    private String status;

    public GoogleResponseModel() {

    }

    public List<GoogleElevationModel> getResults() {

        return results;
    }

    public void setResults(List<GoogleElevationModel> results) {

        this.results = results;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
