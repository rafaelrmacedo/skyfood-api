package com.sky.skyfood.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.skyfood.domain.entity.Restaurant;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.RestaurantRepository;
import com.sky.skyfood.domain.service.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<Restaurant> getById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant r) {
        // utilização do ? significa que podemos retornar qualquer tipo dentro do response entity
        try {
            Restaurant restaurant = restaurantService.save(r);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant r) {

        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

            if (restaurant.isPresent()) {

                BeanUtils.copyProperties(r, restaurant, "id");

                Restaurant savedRestaurant = restaurantService.save(restaurant.get());

                return ResponseEntity.ok(savedRestaurant);
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{restaurantId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long restaurantId,
                                           @RequestBody Map<String, Object> fields) {
        Optional<Restaurant> currentyRestaurant = restaurantRepository.findById(restaurantId);

        if (currentyRestaurant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentyRestaurant.get());

        return update(restaurantId, currentyRestaurant.get());
    }

    private void merge(Map<String, Object> fields, Restaurant currentyRestaurant) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurant originRestaurant = objectMapper.convertValue(fields, Restaurant.class);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Restaurant.class, key);
            field.setAccessible(true); // torna os campos da entidade acessiveis

            Object newValue = ReflectionUtils.getField(field, originRestaurant);

            ReflectionUtils.setField(field, currentyRestaurant, newValue);
        });
    }
}
