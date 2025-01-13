package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CityRepository;
import com.sky.skyfood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.all();
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<City> getCity(@PathVariable Long cityId) {
        City city = cityRepository.getById(cityId);

        if (city != null) {
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody City c) {
        // utilização do ? significa que podemos retornar qualquer tipo dentro do response entity
        try {
            City city = cityService.add(c);

            return ResponseEntity.status(HttpStatus.CREATED).body(city);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City currentCity = cityRepository.getById(cityId);

            if (city != null) {

                BeanUtils.copyProperties(city, currentCity, "id");

                currentCity = cityService.add(currentCity);

                return ResponseEntity.ok(currentCity);
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
