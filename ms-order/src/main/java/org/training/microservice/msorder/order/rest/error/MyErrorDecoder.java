package org.training.microservice.msorder.order.rest.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MyErrorDecoder implements ErrorDecoder {

    @Override
    public FeignClientException decode(final String sParam,
                                       final Response responseParam) {
        ObjectMapper objectMapperLoc = new ObjectMapper();
        try {
            ErrorObj errorObjLoc = objectMapperLoc.readValue(responseParam.body().asInputStream(),
                                                             ErrorObj.class);
            return new FeignClientException(
                    ErrorObj.builder()
                            .withErrorMsgParam("Error while calling another MS")
                            .withErrorCodeParam(3800)
                            .withSubErrorsParam(List.of(errorObjLoc))
                            .build());
        } catch (IOException eParam) {
            return new FeignClientException(
                    ErrorObj.builder()
                            .withErrorMsgParam("Error while decoding errorObj")
                            .withErrorCodeParam(5000)
                            .build());
        }
    }

}
