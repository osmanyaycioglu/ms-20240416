package org.training.microservice.msorder.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.training.microservice.msorder.services.models.Order;

//@Component
@RequiredArgsConstructor
public class CCCallerBean implements CommandLineRunner {
    private final CalleeBean             calleeBean;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final String... args) throws Exception {
        Order                         orderLoc          = new Order();
        CircuitBreaker                myccLoc           = circuitBreakerRegistry.circuitBreaker("mycc");
        CircuitBreaker.Metrics        metricsLoc        = myccLoc.getMetrics();
        CircuitBreaker.EventPublisher eventPublisherLoc = myccLoc.getEventPublisher();
        eventPublisherLoc.onSuccess(e -> System.out.println("success : " + e.toString()))
                         .onError(e -> System.out.println("error : " + e.toString()))
                         .onCallNotPermitted(e -> System.out.println("callnotpermitted : " + e.toString()));
        for (int i = 0; i < 100; i++) {
            System.out.println("Calling Callee : " + i);
            try {
                calleeBean.reserveRestaurant(orderLoc);
            } catch (Exception exp) {
                System.out.println("Error : " + exp.getMessage());
            }

            System.out.println("--------- : " + i
                               + "\nSTATE : "
                               + myccLoc.getState()
                               + "\nFailureRate : "
                               + metricsLoc.getFailureRate()
                               + "\nNumberOfSuccessfulCalls : "
                               + metricsLoc.getNumberOfSuccessfulCalls()
                               + "\nNumberOfFailedCalls : "
                               + metricsLoc.getNumberOfFailedCalls()
                               + "\nNumberOfNotPermittedCalls : "
                               + metricsLoc.getNumberOfNotPermittedCalls()
                               + "\n----*--------"
            );
            try {
                Thread.sleep(500);
            } catch (InterruptedException eParam) {
            }
        }
    }
}
