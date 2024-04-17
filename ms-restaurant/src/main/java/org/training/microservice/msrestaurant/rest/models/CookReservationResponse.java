package org.training.microservice.msrestaurant.rest.models;

import lombok.Builder;
import lombok.Data;

@Data
public class CookReservationResponse {
    private Integer estimatedMinutes;
    private String  note;
    private Long    orderId;

    public CookReservationResponse() {
    }

    @Builder(setterPrefix = "with")
    public CookReservationResponse(final Integer estimatedMinutesParam,
                                   final String noteParam,
                                   final Long orderIdParam) {
        estimatedMinutes = estimatedMinutesParam;
        note             = noteParam;
        orderId          = orderIdParam;
    }
}
