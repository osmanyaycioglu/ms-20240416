package org.training.microservice.msorder.order.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/query")
public class OrderQueryController {

    @GetMapping("/get/one")
    public void getOrder(){
    }
}
