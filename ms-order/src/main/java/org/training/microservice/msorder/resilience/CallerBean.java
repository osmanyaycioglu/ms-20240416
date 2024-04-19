package org.training.microservice.msorder.resilience;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.training.microservice.msorder.services.models.Order;

//@Component
@RequiredArgsConstructor
public class CallerBean implements CommandLineRunner {
    private final CalleeBean    calleeBean;
    private final RetryRegistry retryRegistry;

    @Override
    public void run(final String... args) throws Exception {
        Order                orderLoc          = new Order();
        Retry                xyzLoc            = retryRegistry.retry("xyz");
        Retry.Metrics        metricsLoc        = xyzLoc.getMetrics();
        Retry.EventPublisher eventPublisherLoc = xyzLoc.getEventPublisher();
        eventPublisherLoc.onSuccess(e -> System.out.println("success : " + e.toString()))
                         .onError(e -> System.out.println("error : " + e.toString()))
                         .onRetry(e -> System.out.println("retry : " + e.toString()))
                         .onIgnoredError(e -> System.out.println("ignored : " + e.toString()));
        for (int i = 0; i < 100; i++) {
            System.out.println("Calling Callee : " + i);
            try {
                calleeBean.reserveRestaurant(orderLoc);
            } catch (Exception exp) {
                System.out.println("Error : " + exp.getMessage());
            }

            System.out.println("---------"
                               + "\nSuccessfulCallsWithRetryAttempt : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + "\nSuccessfulCallsWithoutRetryAttempt : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + "\nFailedCallsWithoutRetryAttempt : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + "\nFailedCallsWithRetryAttempt : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt()
                               + "\n----*--------"
            );
        }
    }
}
