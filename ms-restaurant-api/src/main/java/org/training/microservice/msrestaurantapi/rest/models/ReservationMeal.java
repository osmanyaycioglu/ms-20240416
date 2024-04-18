package org.training.microservice.msrestaurantapi.rest.models;

import lombok.Data;

@Data
public class ReservationMeal {
    private String mealName;
    private Double portion;
}
