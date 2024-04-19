package org.training.microservice.msorder.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.training.microservice.msorder.order.rest.models.mappings.IOrderMapping;
import org.training.microservice.msorder.services.models.Order;
import org.training.microservice.msrestaurantapi.rest.models.CookReservation;
import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

@Component
public class CalleeBean {

    private int counter = 0;

    // @Retry(name = "xyz")
    @CircuitBreaker(name = "mycc")
    public CookReservationResponse reserveRestaurant(Order orderParam) {
        counter++;
        if (counter < 20 && counter % 4 == 0) {
            throw new IllegalArgumentException("reserveRestaurant problemi : " + counter);
        }
        if (counter < 20 && counter % 5 == 0) {
            throw new IllegalArgumentException("reserveRestaurant problemi : " + counter);
        }
//        if (counter % 6 == 0) {
//            throw new IllegalArgumentException("reserveRestaurant problemi : " + counter);
//        }
        return CookReservationResponse.builder()
                                      .withOrderIdParam(1000L)
                                      .withNoteParam("xyz")
                                      .build();

    }

}
