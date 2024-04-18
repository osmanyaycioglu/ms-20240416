package org.training.microservice.msorder.services.models;

import lombok.Builder;
import lombok.Data;

@Data
public class Meal {
    private String mealName;
    private Double portion;


    public Meal() {
    }

    @Builder
    public Meal(final String mealNameParam,
                final Double portionParam) {
        mealName = mealNameParam;
        portion  = portionParam;
    }
}
