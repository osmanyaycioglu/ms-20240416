package org.training.microservice.msorder.order.rest.models;

import lombok.Builder;
import lombok.Data;
import org.training.microservice.msorder.services.models.Customer;
import org.training.microservice.msorder.services.models.EOrderStatus;
import org.training.microservice.msorder.services.models.Meal;

import java.util.List;

@Data
public class OrderQueryResponse {
    private List<Meal> orderedMeals;
    private     int        deliveryMinute;
    private     EOrderStatus orderStatus;
    private Customer customer;

    public OrderQueryResponse() {
    }

    @Builder(setterPrefix = "with")
    public OrderQueryResponse(final List<Meal> orderedMealsParam,
                              final int deliveryMinuteParam,
                              final EOrderStatus orderStatusParam) {
        orderedMeals   = orderedMealsParam;
        deliveryMinute = deliveryMinuteParam;
        orderStatus    = orderStatusParam;
    }
}
