package org.training.microservice.msrestaurantapi.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.training.microservice.msrestaurantapi.rest.models.CookReservation;
import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

public interface IRestaurantCookReservationController {
    @PostMapping(value = "/api/v1/restaurant/cook/reserve", consumes = MediaType.APPLICATION_JSON_VALUE)
    CookReservationResponse reserve(@RequestBody CookReservation cookReservationParam);

}
