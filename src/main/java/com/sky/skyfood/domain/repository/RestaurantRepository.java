package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> all();
    Restaurant getById(Long id);
    Restaurant add(Restaurant restaurant);
    void remove(Long id);

}
