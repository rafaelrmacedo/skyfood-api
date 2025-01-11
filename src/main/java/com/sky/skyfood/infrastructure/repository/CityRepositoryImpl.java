package com.sky.skyfood.infrastructure.repository;

import com.sky.skyfood.domain.entity.City;
import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.repository.CityRepository;
import com.sky.skyfood.domain.repository.CuisineRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<City> all() {
        return entityManager.createQuery("from City", City.class).getResultList();
    }

    @Override
    public City getById(Long id) {
        return entityManager.find(City.class, id);
    }

    @Override
    @Transactional
    public City add(City city) {
        return entityManager.merge(city);
    }

    @Override
    @Transactional
    public void remove(City city) {
        city = getById(city.getId());
        entityManager.remove(city);
    }
}
