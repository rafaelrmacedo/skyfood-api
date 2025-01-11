package com.sky.skyfood.infrastructure.repository;

import com.sky.skyfood.domain.entity.Cuisine;
import com.sky.skyfood.domain.repository.CuisineRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CuisineRepositoryImpl implements CuisineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cuisine> all() {
        return entityManager.createQuery("from Cuisine", Cuisine.class).getResultList();
    }

    @Override
    public Cuisine getById(Long id) {
        return entityManager.find(Cuisine.class, id);
    }

    @Override
    @Transactional
    public Cuisine add(Cuisine cuisine) {
        return entityManager.merge(cuisine);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        entityManager.remove(id);
    }
}
