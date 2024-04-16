package org.training.microservice.msorder.orders;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController {

    @PostMapping("/process")
    public void processOrder(){

    }

    @GetMapping("/delete")
    public void delete(){
    }

}
