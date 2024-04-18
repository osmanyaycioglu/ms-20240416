package org.training.microservice.msorder.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.msorder.order.rest.error.ErrorObj;
import org.training.microservice.msorder.order.rest.models.mappings.IOrderMapping;
import org.training.microservice.msorder.services.models.Order;
import org.training.microservice.msrestaurantapi.rest.models.CookReservation;
import org.training.microservice.msrestaurantapi.rest.models.CookReservationResponse;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantIntegration {
    private final RestTemplate                restTemplate;
    private final EurekaClient                eurekaClient;
    private final IRestaurantFeignIntegration restaurantFeignIntegration;
    private final CircuitBreakerRegistry      circuitBreakerRegistry;

    @Retry(name = "xyz")
    @CircuitBreaker(name = "mycc", fallbackMethod = "reserveRestaurantFallback")
    public CookReservationResponse reserveRestaurant(Order orderParam) {
        CookReservation cookReservationLoc = new CookReservation();
        cookReservationLoc.setReservationMeals(IOrderMapping.INSTANCE.toReservationMeals(orderParam.getOrderedMeals()));
        cookReservationLoc.setDeliveryPeriod(orderParam.getDeliveryMinute());
        return restTemplate.postForObject("http://MS-RESTAURANT/api/v1/restaurant/cook/reserve",
                                          cookReservationLoc,
                                          CookReservationResponse.class);


    }

    public CookReservationResponse reserveRestaurantFallback(Order orderParam,
                                                             Exception exceptionParam) {
        io.github.resilience4j.circuitbreaker.CircuitBreaker       myccLoc  = circuitBreakerRegistry.circuitBreaker("mycc");
        io.github.resilience4j.circuitbreaker.CircuitBreaker.State stateLoc = myccLoc.getState();
        return null;
    }


    private long index = 0;

    @Retry(name = "abc")
    public CookReservationResponse reserveRestaurant2(Order orderParam) {
        Application        applicationLoc = eurekaClient.getApplication("MS-RESTAURANT");
        List<InstanceInfo> instancesLoc   = applicationLoc.getInstances();
        if (instancesLoc != null && !instancesLoc.isEmpty()) {
            int localIndex = (int) (index % instancesLoc.size());
            index++;
            InstanceInfo    instanceInfoLoc    = instancesLoc.get(localIndex);
            CookReservation cookReservationLoc = new CookReservation();
            cookReservationLoc.setReservationMeals(IOrderMapping.INSTANCE.toReservationMeals(orderParam.getOrderedMeals()));
            cookReservationLoc.setDeliveryPeriod(orderParam.getDeliveryMinute());
            RestTemplate restTemplateLoc = new RestTemplate();
            return restTemplateLoc.postForObject("http://"
                                                 + instanceInfoLoc.getIPAddr()
                                                 + ":"
                                                 + instanceInfoLoc.getPort()
                                                 + "/api/v1/restaurant/cook/reserve",
                                                 cookReservationLoc,
                                                 CookReservationResponse.class);

        }
        throw new IllegalStateException("boş liste");
    }

    public CookReservationResponse reserveRestaurant3(Order orderParam) {
        CookReservation cookReservationLoc = new CookReservation();
        cookReservationLoc.setReservationMeals(IOrderMapping.INSTANCE.toReservationMeals(orderParam.getOrderedMeals()));
        cookReservationLoc.setDeliveryPeriod(orderParam.getDeliveryMinute());
        return restaurantFeignIntegration.reserve(cookReservationLoc);

    }

}
