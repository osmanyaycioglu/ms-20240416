package org.training.microservice.msorder.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.microservice.msrestaurantapi.rest.IRestaurantCookReservationController;

@FeignClient(value = "MS-RESTAURANT",contextId = "ctx1")
public interface IRestaurantFeignIntegration extends IRestaurantCookReservationController {


}
