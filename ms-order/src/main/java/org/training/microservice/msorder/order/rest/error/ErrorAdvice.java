package org.training.microservice.msorder.order.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.training.microservice.msorder.order.rest.MyException;

import java.util.stream.Collectors;


@RestControllerAdvice
public class ErrorAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorMsgParam(exp.getMessage())
                       .withErrorCodeParam(1001)
                       .build();
    }
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorObj handle(NoResourceFoundException exp) {
        return ErrorObj.builder()
                       .withErrorMsgParam(exp.getMessage())
                       .withErrorCodeParam(4004)
                       .build();
    }


    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MyException exp) {
        return ErrorObj.builder()
                       .withErrorMsgParam("problem")
                       .withErrorCodeParam(1002)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorMsgParam("validation error")
                       .withErrorCodeParam(1003)
                       .withSubErrorsParam(exp.getAllErrors()
                                              .stream()
                                              .map(se -> ErrorObj.builder()
                                                                 .withErrorMsgParam(se.toString())
                                                                 .withErrorCodeParam(1005)
                                                                 .build())
                                              .collect(Collectors.toList()))
                       .build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handle(Exception exp) {
        logger.error("[ErrorAdvice][handle]-> *Error* : " + exp.getMessage(),exp);
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                                 .body(ErrorObj.builder()
                                               .withErrorMsgParam(exp.getMessage())
                                               .withErrorCodeParam(5001)
                                               .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorMsgParam(exp.getMessage())
                                           .withErrorCodeParam(5001)
                                           .build());
    }

}
