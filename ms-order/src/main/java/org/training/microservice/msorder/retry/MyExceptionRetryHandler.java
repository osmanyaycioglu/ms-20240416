package org.training.microservice.msorder.retry;

import org.training.microservice.msorder.order.rest.error.ErrorObj;
import org.training.microservice.msorder.order.rest.error.FeignClientException;

import java.util.function.Predicate;

public class MyExceptionRetryHandler implements Predicate<Exception> {

    @Override
    public boolean test(final Exception exceptionParam) {
        if (exceptionParam instanceof NullPointerException) {
            return false;
        }
        if (exceptionParam instanceof FeignClientException) {
            FeignClientException feignClientExceptionLoc = (FeignClientException) exceptionParam;
            ErrorObj             errorObjLoc             = feignClientExceptionLoc.getErrorObj();
            switch (errorObjLoc.getErrorCode()) {
                case 1004:
                    return false;
                default:
                    return  true;
            }
        }
        return true;
    }

}
