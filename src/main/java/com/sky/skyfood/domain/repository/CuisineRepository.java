package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

//    List<Cuisine> getByName(String name);

}
