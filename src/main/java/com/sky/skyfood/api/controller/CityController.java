package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CityRepository;
import com.sky.skyfood.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<City> getCity(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    // utilização do ? significa que podemos retornar qualquer tipo dentro do response entity
    public ResponseEntity<?> add(@RequestBody City c) {
        try {
            City city = cityService.save(c);

            return ResponseEntity.status(HttpStatus.CREATED).body(city);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            Optional<City> currentCity = cityRepository.findById(cityId);

            if (currentCity.isPresent()) {

                BeanUtils.copyProperties(city, currentCity.get(), "id");

                City savedCity = cityService.save(city);

                return ResponseEntity.ok(savedCity);
            }

            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> remove(@PathVariable Long cityId) {
        try {
            cityService.deleteById(cityId);
            return ResponseEntity.ok().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
