package com.sky.skyfood.domain.service;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.exception.CuisineInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CuisineService {

    @Autowired
    private CuisineRepository cuisineRepository;

    public Cuisine add(Cuisine cuisine) {
        return cuisineRepository.add(cuisine);
    }

    public void remove (Long id) {
        try {
            cuisineRepository.remove(id);
        } catch (DataIntegrityViolationException e) {
            throw new CuisineInUseException("The cuisine can't be removed, because it's in use");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("The cuisine can't be removed, because it does not exist");
        }
    }
}
