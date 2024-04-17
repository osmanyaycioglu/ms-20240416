package org.training.microservice.msrestaurant.rest.models;

import lombok.Data;

import java.util.List;

@Data
public class CookReservation {
    private List<Meal> meals;
    private Integer deliveryPeriod;
}
