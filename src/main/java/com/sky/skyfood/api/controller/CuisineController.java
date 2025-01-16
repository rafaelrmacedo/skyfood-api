package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.entity.Restaurant;
import com.sky.skyfood.domain.entity.State;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CuisineRepository;
import com.sky.skyfood.domain.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineService cuisineService;

    @GetMapping
    public List<Cuisine> list() {
        return cuisineRepository.all();
    }

    @GetMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> getById(@PathVariable Long cuisineId) {
        Cuisine cuisine = cuisineRepository.getById(cuisineId);

        if (cuisine != null) {
            return ResponseEntity.ok(cuisine);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cuisine add(@RequestBody Cuisine c) {
        return cuisineRepository.add(c);
    }

    @PutMapping(value = "/{cuisineId}")
    public ResponseEntity<Cuisine> update(@PathVariable Long cuisineId, @RequestBody Cuisine c) {
        Cuisine cuisine = cuisineRepository.getById(cuisineId); // para casos de apenas busca, é ok chamar o repo

        if (cuisine != null) {
            cuisine.setName(c.getName());

            cuisine = cuisineService.add(cuisine);

            return ResponseEntity.ok(cuisine);
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
