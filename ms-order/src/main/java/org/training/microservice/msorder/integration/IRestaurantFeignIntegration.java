package org.training.microservice.msorder.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.microservice.msorder.integration.models.CookReservation;

@FeignClient("MS-RESTAURANT")
public interface IRestaurantFeignIntegration {

    @PostMapping("/api/v1/restaurant/cook/reserve")
    CookReservationResponse reserve(@RequestBody CookReservation cookReservationParam);

}
