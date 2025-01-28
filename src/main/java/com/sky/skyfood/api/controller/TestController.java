package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping("/cuisines/byName")
    public List<Cuisine> cuisineByName(String name){
        return cuisineRepository.findByName(name);
    }
}
