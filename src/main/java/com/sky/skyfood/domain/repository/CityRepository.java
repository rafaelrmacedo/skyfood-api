package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
