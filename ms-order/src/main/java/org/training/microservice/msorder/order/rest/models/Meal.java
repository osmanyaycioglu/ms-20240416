package org.training.microservice.msorder.order.rest.models;

import lombok.Data;

@Data
public class Meal {
    private String mealName;
    private Double portion;
}
