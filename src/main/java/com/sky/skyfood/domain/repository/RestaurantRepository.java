package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries,
        JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r join fetch r.cuisine left join fetch r.paymentMethod")
    List<Restaurant> findAll();
}
