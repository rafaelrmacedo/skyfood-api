package com.sky.skyfood.domain.service;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.exception.EntityInUseException;
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

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void remove (Long id) {
        try {
            cuisineRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("The cuisine can't be removed, because it's in use");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("The cuisine can't be removed, because it does not exist");
        }
    }
}
