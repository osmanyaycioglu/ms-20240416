package org.training.microservice.msorder.order.rest;

import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.order.rest.models.OrderQueryRequest;
import org.training.microservice.msorder.order.rest.models.OrderQueryResponse;
import org.training.microservice.msorder.services.models.EOrderStatus;
import org.training.microservice.msorder.services.models.Meal;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/query")
public class OrderQueryController {

    @GetMapping("/get/one")
    public void getOrder() {
    }

    @PostMapping("/get/customer/all/orders")
    public List<OrderQueryResponse> getAllOrders(@RequestBody OrderQueryRequest requestParam) {
        return List.of(OrderQueryResponse.builder()
                                         .withOrderStatusParam(EOrderStatus.IN_PROGRESS)
                                         .withDeliveryMinuteParam(15)
                                         .withOrderedMealsParam(List.of(Meal.builder()
                                                                            .mealNameParam("kebap")
                                                                            .portionParam(1D)
                                                                            .build()))
                                         .build(),
                       OrderQueryResponse.builder()
                                         .withOrderStatusParam(EOrderStatus.DELIVERED)
                                         .withDeliveryMinuteParam(0)
                                         .withOrderedMealsParam(List.of(Meal.builder()
                                                                            .mealNameParam("lahmacun")
                                                                            .portionParam(2D)
                                                                            .build()))
                                         .build());
    }

}
