package org.training.microservice.msorder.order.rest.models.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.training.microservice.msorder.order.rest.models.MealRestObj;
import org.training.microservice.msorder.order.rest.models.OrderRequest;
import org.training.microservice.msorder.services.models.Meal;
import org.training.microservice.msorder.services.models.Order;
import org.training.microservice.msrestaurantapi.rest.models.ReservationMeal;

import java.util.List;

@Mapper()
public interface IOrderMapping {

    IOrderMapping INSTANCE = Mappers.getMapper(IOrderMapping.class);

    @Mapping(target = "orderedMeals",source = "meals")
    @Mapping(target = "customer.customerName",source = "customerName")
    @Mapping(target = "customer.customerSurname",source = "customerSurname")
    @Mapping(target = "customer.customerNumber",source = "customerNumber")
    Order toOrder(OrderRequest order);

    Meal toMeal(MealRestObj meal);

    @Mapping(source = "orderedMeals",target = "meals")
    @Mapping(source = "customer.customerName",target = "customerName")
    @Mapping(source = "customer.customerSurname",target = "customerSurname")
    @Mapping(source = "customer.customerNumber",target = "customerNumber")
    OrderRequest toOrderRequest(Order order);


    Meal toMeal(ReservationMeal mealParam);

    ReservationMeal toReservationMeal(Meal mealParam);

    List<Meal> toMeals(List<ReservationMeal> mealParam);

    List<ReservationMeal> toReservationMeals(List<Meal> mealParam);

}
