# Weather App

A weather application featuring Java 8 with reactive streams, World Weather API and Google Elevation API. 

## Problem
Create a simple weather application and retrieve weather data for a given location.

## Implementation
* Rest API exposed using Java Spring
* Using [World Weather API](https://developer.worldweatheronline.com/) to retrieve Weather information.
* Using [Google Elevation API](https://developers.google.com/maps/documentation/elevation/start) to retrieve Elevation for a given latitude and longitude.
* For a given input, eg. Sydney:
  * Lookup weather information via the World Weather API and lookup its elevation via Google Elevation API.

## Getting Started

These instructions will get you a copy of the project up and running on your **local machine** for development and testing purposes.

### Prerequisites

```
Java8
Gradle 4.1 (Or just use provided Gradle wrapper)

```

## Running Unit tests

Run all tests:

```
gradlew test
```
Run Component test:
   
```
gradlew componentTest -i

```

Sample output from Component Test:
```
.
.
.
 com.hafidsousa.weather.ComponentTest     : Sydney|-33.883,151.217,56.49151992797852|2017-10-06T12:20|Overcast|18.0|1015.0|73.0
 com.hafidsousa.weather.ComponentTest     : London|51.517,-0.106,9.750859260559082|2017-10-06T02:20|Clear|12.0|1023.0|71.0
 com.hafidsousa.weather.ComponentTest     : Dubai|25.252,55.28,4.109510898590088|2017-10-06T05:21|Clear|30.0|1007.0|84.0
 com.hafidsousa.weather.ComponentTest     : Melbourne|-37.817,144.967,25.52762794494629|2017-10-06T12:22|Partly cloudy|14.0|1023.0|51.0
 com.hafidsousa.weather.ComponentTest     : Santiago|-33.45,-70.667,543.6805419921875|2017-10-05T22:23|Partly cloudy|12.0|1022.0|82.0
.
.
.
```

## Built With

* [Gradle 4.1](https://docs.gradle.org/4.1/userguide/userguide.html) - Dependency Management
* [Spring Boot 2](https://docs.spring.io/spring-boot/docs/2.0.0.M4/reference/htmlsingle/) - Web framework
	* [Spring 5 Web Reactive](https://docs.spring.io/spring-framework/docs/5.0.0.M4/spring-framework-reference/htmlsingle/) - Spring Support for Web Reactive
	* [Reactor](https://projectreactor.io/) - Reactive Core
	* [Netty io]( https://netty.io/) - Web Client

## Author

* **[Hafid F Sousa](https://github.com/hafidsousa)**

## License

This project is licensed under the [Apache License 2.0](LICENSE).
