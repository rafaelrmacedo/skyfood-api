package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Cuisine;

import java.util.List;

public interface CuisineRepository {

    List<Cuisine> all();
    Cuisine getById(Long id);
    Cuisine add(Cuisine cuisine);
    void remove(Long id);

}
