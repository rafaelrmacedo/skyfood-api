package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.entity.Restaurant;
import com.sky.skyfood.domain.entity.State;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CuisineRepository;
import com.sky.skyfood.domain.service.CuisineService;
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
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.findAll();
    }

    @GetMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> getById(@PathVariable Long cuisineId) {
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);

        /*
        * if (cuisine.isPresent()) {
            return ResponseEntity.ok(cuisine.get());
        }

        return ResponseEntity.notFound().build();
        * */

        return cuisine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine c) {
        return cuisineRepository.save(c);
    }

    @PutMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId, @RequestBody Cuisine c) {
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);

        if (cuisine.isPresent()) {
            BeanUtils.copyProperties(c, cuisine.get(), "id");

            Cuisine savedCuisine = cuisineRepository.save(cuisine.get());
            return ResponseEntity.ok(savedCuisine);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> remove(@PathVariable Long cuisineId) {
        try {
            cuisineService.remove(cuisineId);
            return ResponseEntity.noContent().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
