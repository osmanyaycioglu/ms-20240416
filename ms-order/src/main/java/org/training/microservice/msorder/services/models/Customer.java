package org.training.microservice.msorder.services.models;

import lombok.Data;

@Data
public class Customer {
    private Long   customerId;
    private String customerName;
    private String customerSurname;
    private String customerNumber;

}
