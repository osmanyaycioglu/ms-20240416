package org.training.microservice.msorder.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.training.microservice.msorder.order.rest.models.Meal;

import java.util.List;

@Data
public class Order {

    private String       customerName;
    private String       customerSurname;
    private String       customerNumber;
    private List<Meal>   meals;
    private int          deliveryMinute;
    private EOrderStatus orderStatus;
}
