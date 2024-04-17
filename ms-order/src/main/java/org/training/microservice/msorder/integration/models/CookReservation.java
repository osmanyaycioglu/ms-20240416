package org.training.microservice.msorder.integration.models;

import lombok.Data;
import org.training.microservice.msorder.services.models.Meal;

import java.util.List;

@Data
public class CookReservation {
    private List<Meal> meals;
    private Integer    deliveryPeriod;
}
