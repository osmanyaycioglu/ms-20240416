package org.training.microservice.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.msorder.integration.models.CookReservation;
import org.training.microservice.msorder.services.models.Order;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantIntegration {
    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
    private final IRestaurantFeignIntegration restaurantFeignIntegration;


    public CookReservationResponse reserveRestaurant(Order orderParam) {
        CookReservation cookReservationLoc = new CookReservation();
        cookReservationLoc.setMeals(orderParam.getOrderedMeals());
        cookReservationLoc.setDeliveryPeriod(orderParam.getDeliveryMinute());
        return restTemplate.postForObject("http://MS-RESTAURANT/api/v1/restaurant/cook/reserve",
                                          cookReservationLoc,
                                          CookReservationResponse.class);

    }

    private long index = 0;

    public CookReservationResponse reserveRestaurant2(Order orderParam) {
        Application        applicationLoc = eurekaClient.getApplication("MS-RESTAURANT");
        List<InstanceInfo> instancesLoc   = applicationLoc.getInstances();
        if (instancesLoc != null && !instancesLoc.isEmpty()) {
            int             localIndex         = (int) (index % instancesLoc.size());
            index++;
            InstanceInfo    instanceInfoLoc    = instancesLoc.get(localIndex);
            CookReservation cookReservationLoc = new CookReservation();
            cookReservationLoc.setMeals(orderParam.getOrderedMeals());
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
        cookReservationLoc.setMeals(orderParam.getOrderedMeals());
        cookReservationLoc.setDeliveryPeriod(orderParam.getDeliveryMinute());
        return restaurantFeignIntegration.reserve(cookReservationLoc);

    }

}
