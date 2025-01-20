package com.sky.skyfood.domain.service;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.entity.Restaurant;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CuisineRepository;
import com.sky.skyfood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    public Restaurant save(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();

        Cuisine cuisine = cuisineRepository.findById(cuisineId)
                .orElseThrow(() -> new EntityNotFoundException("Cuisine not found"));

        restaurant.setCuisine(cuisine);

        return restaurantRepository.add(restaurant);
    }

    public void remove(Long id) {
        try {
            restaurantRepository.remove(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("The cuisine can't be removed, because it's in use");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("The restaurant can't be removed, because it does not exist");
        }
    }
}
