package org.training.microservice.msrestaurant.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.microservice.msrestaurant.rest.models.CookReservation;
import org.training.microservice.msrestaurant.rest.models.CookReservationResponse;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/restaurant/cook")
public class RestaurantCookReservationController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/reserve")
    public CookReservationResponse reserve(@RequestBody CookReservation cookReservationParam) {
        return CookReservationResponse.builder()
                                      .withEstimatedMinutesParam(20)
                                      .withNoteParam("20 dak sonra gel al : " + port)
                                      .withOrderIdParam(new Random().nextLong())
                                      .build();
    }

}
