package com.sky.skyfood.domain.service;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City add(City city) {
        return cityRepository.add(city);
    }

    public void remove (Long id) {
        try {
            cityRepository.remove(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("The cuisine can't be removed, because it's in use");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("The cuisine can't be removed, because it does not exist");
        }
    }
}
