package com.hafidsousa.weather.model.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Hafid Ferreira Sousa
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExternalResponseModel {

    @JsonProperty("nearest_area")
    private List<ExternalAreaModel> nearestArea;

    @JsonProperty("time_zone")
    private List<ExternalTimezoneModel> timeZone;

    @JsonProperty("current_condition")
    private List<ExternalCurrentConditionModel> currentCondition;

    public ExternalResponseModel() {

    }

    public List<ExternalAreaModel> getNearestArea() {

        return nearestArea;
    }

    public void setNearestArea(List<ExternalAreaModel> nearestArea) {

        this.nearestArea = nearestArea;
    }

    public List<ExternalTimezoneModel> getTimeZone() {

        return timeZone;
    }

    public void setTimeZone(List<ExternalTimezoneModel> timeZone) {

        this.timeZone = timeZone;
    }

    public List<ExternalCurrentConditionModel> getCurrentCondition() {

        return currentCondition;
    }

    public void setCurrentCondition(List<ExternalCurrentConditionModel> currentCondition) {

        this.currentCondition = currentCondition;
    }
}
