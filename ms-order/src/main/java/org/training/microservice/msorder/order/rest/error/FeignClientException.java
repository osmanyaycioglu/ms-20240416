package org.training.microservice.msorder.order.rest.error;

import lombok.Data;

@Data
public class FeignClientException extends RuntimeException {
    private ErrorObj errorObj;

    public FeignClientException() {
    }

    public FeignClientException(final String message,
                                ErrorObj errorObjParam
    ) {
        super(message);
        errorObj = errorObjParam;
    }

    public FeignClientException(final ErrorObj errorObjParam) {
        errorObj = errorObjParam;
    }
}
