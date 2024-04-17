package org.training.microservice.msrestaurant.rest.models;

import lombok.Data;

@Data
public class Meal {
    private String mealName;
    private Double portion;
}
