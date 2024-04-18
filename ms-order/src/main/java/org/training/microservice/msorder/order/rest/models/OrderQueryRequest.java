package org.training.microservice.msorder.order.rest.models;

import lombok.Data;

@Data
public class OrderQueryRequest {
    private Long customerId;
    private int orderCount;

}
