package org.training.microservice.msorder.services.models;

import lombok.Data;
import org.training.microservice.msorder.order.rest.models.MealRestObj;

import java.util.List;

@Data
public class Order {

    private List<Meal>   orderedMeals;
    private int          deliveryMinute;
    private EOrderStatus orderStatus;
    private Customer     customer;

}
