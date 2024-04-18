package org.training.microservice.msorder.services.models;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private List<Meal>   orderedMeals;
    private int          deliveryMinute;
    private EOrderStatus orderStatus;
    private Customer     customer;

}
