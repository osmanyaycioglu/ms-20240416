package org.training.microservice.msorder.order.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.order.rest.error.ErrorObj;
import org.training.microservice.msorder.order.rest.models.OrderRequest;
import org.training.microservice.msorder.order.rest.models.OrderRestObj;
import org.training.microservice.msorder.services.OrderProcessService;

@RestController
@RequestMapping("/api/v1/order/management")
@RequiredArgsConstructor
public class OrderManagementController {
    private final OrderProcessService orderProcessService;

    @PostMapping("/process")
    public OrderRestObj processOrder(@Valid @RequestBody OrderRequest orderRequestParam) throws MyException {
        if (orderRequestParam.getMeals().size() > 100) {
            throw new MyException();
        }
        orderProcessService.process();
        return null;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long orderId){
        return "OK";
    }

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MyException exp) {
        return ErrorObj.builder()
                       .withErrorMsgParam("another problem")
                       .withErrorCodeParam(2002)
                       .build();
    }

}
