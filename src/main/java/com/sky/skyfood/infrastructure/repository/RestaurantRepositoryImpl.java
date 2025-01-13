package com.sky.skyfood.infrastructure.repository;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.entity.Restaurant;
import com.sky.skyfood.domain.repository.RestaurantRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurant> all() {
        return entityManager.createQuery("from Restaurant ", Restaurant.class).getResultList();
    }

    @Override
    public Restaurant getById(Long id) {
        return entityManager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public Restaurant add(Restaurant restaurant) {
        return entityManager.merge(restaurant);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Restaurant restaurant = getById(id);

        if (restaurant == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(restaurant);
    }
}
