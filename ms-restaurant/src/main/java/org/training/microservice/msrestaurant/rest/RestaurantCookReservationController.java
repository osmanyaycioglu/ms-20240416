package org.training.microservice.msrestaurant.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.training.microservice.msrestaurantapi.rest.IRestaurantCookReservationController;
import org.training.microservice.msrestaurantapi.rest.models.CookReservation;
import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

import java.util.Random;

@RestController
public class RestaurantCookReservationController implements IRestaurantCookReservationController {

    @Value("${server.port}")
    private Integer port;

    public CookReservationResponse reserve(@RequestBody CookReservation cookReservationParam) {
        return CookReservationResponse.builder()
                                      .withEstimatedMinutesParam(20)
                                      .withNoteParam("20 dak sonra gel al : " + port)
                                      .withOrderIdParam(new Random().nextLong())
                                      .build();
    }

}
