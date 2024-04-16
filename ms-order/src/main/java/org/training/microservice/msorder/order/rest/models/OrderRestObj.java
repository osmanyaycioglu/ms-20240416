package org.training.microservice.msorder.order.rest.models;

import lombok.Data;

@Data
public class OrderRestObj {
    private Long orderId;
    private String orderStatus;
}
