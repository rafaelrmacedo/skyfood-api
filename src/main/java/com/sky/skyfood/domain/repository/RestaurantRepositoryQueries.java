package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestaurantRepositoryQueries {
    List<Restaurant> find(String name,
                          BigDecimal taxDeliveryFirst, BigDecimal taxDeliveryLast);

    List<Restaurant> findWithTaxFree(String name);
}
