package org.training.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.RestaurantIntegration;
import org.training.microservice.msorder.integration.SendMessageIntegration;
import org.training.microservice.msorder.services.models.Order;
import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final RestaurantIntegration  restaurantIntegration;
    private final SendMessageIntegration sendMessageIntegration;

    public CookReservationResponse process(Order orderParam) {
        return restaurantIntegration.reserveRestaurant(orderParam);

    }

    public CookReservationResponse process2(Order orderParam) {
        return restaurantIntegration.reserveRestaurant2(orderParam);

    }

    public CookReservationResponse process3(Order orderParam) {
        CookReservationResponse cookReservationResponseLoc = restaurantIntegration.reserveRestaurant3(orderParam);
        sendMessageIntegration.sendSMS(cookReservationResponseLoc.getNote(),
                                       orderParam.getCustomer()
                                                 .getCustomerNumber());
        return restaurantIntegration.reserveRestaurant3(orderParam);

    }


}
