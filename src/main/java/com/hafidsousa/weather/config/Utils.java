package com.hafidsousa.weather.config;

import com.hafidsousa.weather.model.WeatherConditionsModel;
import com.hafidsousa.weather.model.WeatherModel;
import com.hafidsousa.weather.model.external.ExternalWeatherModel;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Hafid Ferreira Sousa
 */
public final class Utils {

    public static WeatherModel convertToInternalModel(ExternalWeatherModel externalWeatherModel) {

        WeatherModel weatherModel = new WeatherModel();
        WeatherConditionsModel weatherConditionsModel = new WeatherConditionsModel();

        if (!CollectionUtils.isEmpty(externalWeatherModel.getData().getNearestArea()))
        {
            weatherModel.setLatitude(
                    externalWeatherModel.getData().getNearestArea().get(0).getLatitude()
            );
            weatherModel.setLongitude(
                    externalWeatherModel.getData().getNearestArea().get(0).getLongitude()
            );

            if (!CollectionUtils.isEmpty(externalWeatherModel.getData().getNearestArea().get(0).getAreaName()))
            {
                weatherModel.setLocation(
                        externalWeatherModel.getData().getNearestArea().get(0).getAreaName().get(0).getValue()
                );
            }
        }

        if (!CollectionUtils.isEmpty(externalWeatherModel.getData().getCurrentCondition()))
        {

            externalWeatherModel.getData().getCurrentCondition().forEach(
                    (externalCurrentConditionModel -> {
                        weatherConditionsModel.setHumidity(
                                Double.valueOf(externalCurrentConditionModel.getHumidity())
                        );

                        weatherConditionsModel.setPressure(
                                Double.valueOf(externalCurrentConditionModel.getPressure())
                        );

                        weatherConditionsModel.setTemperature(
                                Double.valueOf(externalCurrentConditionModel.getTemperatureCelsius())
                        );

                        if (!CollectionUtils.isEmpty(
                                externalCurrentConditionModel.getWeatherDesc())
                                )
                        {
                            weatherConditionsModel.setConditions(
                                    externalCurrentConditionModel.getWeatherDesc().get(0).getValue()
                            );
                        }

                        weatherModel.getConditions().add(weatherConditionsModel);
                    })
            );
        }

        if (!CollectionUtils.isEmpty(externalWeatherModel.getData().getTimeZone()))
        {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            weatherConditionsModel.setLocalTime(
                    LocalDateTime.parse(externalWeatherModel.getData().getTimeZone().get(0).getLocalTime(), formatter)
            );
        }

        return weatherModel;
    }

    public static final class params {

        public static final String key = "key";

        public static final String query = "q";

        public static final String format = "format";

        public static final String numOfDays = "num_of_days";

        public static final String includeLocation = "includelocation";

        public static final String showLocaltime = "showlocaltime";

        public static final String locations = "locations";
    }

    public static final class error {

        public static final String failed_get_url = "Unable to Get URL {}";
    }
}
