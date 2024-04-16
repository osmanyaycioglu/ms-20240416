package org.training.microservice.msorder.order.rest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.order.rest.models.OrderRequest;
import org.training.microservice.msorder.order.rest.models.OrderRestObj;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController {

    @PostMapping("/process")
    public OrderRestObj processOrder(@Valid @RequestBody OrderRequest orderRequestParam){
        return null;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long orderId){
        return "OK";
    }

}
