package com.sky.skyfood.infrastructure.repository;

import com.sky.skyfood.domain.entity.State;
import com.sky.skyfood.domain.repository.StateRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<State> all() {
        return entityManager.createQuery("from State", State.class).getResultList();
    }

    @Override
    public State getById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    @Transactional
    public State add(State state) {
        return entityManager.merge(state);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        State state = getById(id);

        entityManager.remove(state);
    }
}
