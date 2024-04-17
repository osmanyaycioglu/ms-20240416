package org.training.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.CookReservationResponse;
import org.training.microservice.msorder.integration.RestaurantIntegration;
import org.training.microservice.msorder.services.models.Order;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final RestaurantIntegration restaurantIntegration;

    public CookReservationResponse process(Order orderParam){
        return restaurantIntegration.reserveRestaurant(orderParam);

    }

    public CookReservationResponse process2(Order orderParam){
        return restaurantIntegration.reserveRestaurant2(orderParam);

    }

    public CookReservationResponse process3(Order orderParam){
        return restaurantIntegration.reserveRestaurant3(orderParam);

    }


}
