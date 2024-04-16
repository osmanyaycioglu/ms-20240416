package org.training.microservice.msorder.order.rest.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    @NotBlank
    @Size(min = 2,max = 20)
    private String customerName;
    @NotBlank
    @Size(min = 3,max = 25)
    private String customerSurname;
    @NotBlank
    @Size(min = 10,max = 13)
    private String customerNumber;
    @Size(min = 1)
    private List<Meal> meals;
    @Min(10)
    @Max(60)
    private int deliveryMinute;

}
