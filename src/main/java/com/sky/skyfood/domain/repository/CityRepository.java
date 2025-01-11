package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.entity.Cuisine;

import java.util.List;

public interface CityRepository {

    List<City> all();
    City getById(Long id);
    City add(City city);
    void remove(City city);

}
