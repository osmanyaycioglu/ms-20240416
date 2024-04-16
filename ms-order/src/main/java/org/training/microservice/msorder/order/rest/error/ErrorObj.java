package org.training.microservice.msorder.order.rest.error;


import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
public class ErrorObj {
    private String microservice;
    private String boundedContext;

    private List<ErrorObj> subErrors;
    private String         errorMsg;
    private int            errorCode;


    public ErrorObj() {
    }

    @Builder(setterPrefix = "with")
    public ErrorObj(final String microserviceParam,
                    final String boundedContextParam,
                    final List<ErrorObj> subErrorsParam,
                    final String errorMsgParam,
                    final int errorCodeParam) {
        microservice   = microserviceParam;
        boundedContext = boundedContextParam;
        subErrors      = subErrorsParam;
        errorMsg       = errorMsgParam;
        errorCode      = errorCodeParam;
    }
}
