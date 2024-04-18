package org.training.microservice.msrestaurantapi.rest.models;

import lombok.Data;

import java.util.List;

@Data
public class CookReservation {
    private List<ReservationMeal> reservationMeals;
    private Integer               deliveryPeriod;
}
