package org.training.microservice.msorder.retry;

import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

import java.util.function.Predicate;

public class MyReturnRetryHandler implements Predicate<CookReservationResponse> {
    @Override
    public boolean test(final CookReservationResponse cookReservationResponseParam) {
        if (cookReservationResponseParam.getOrderId() == null) {
            return true;
        }
        return false;
    }
}
