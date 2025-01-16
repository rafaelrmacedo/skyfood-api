package com.sky.skyfood.domain.service;

import com.sky.skyfood.domain.entity.State;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public State add(State state) {
        return stateRepository.add(state);
    }

    public void remove (Long id) {
        try {
            stateRepository.remove(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException("The state can't be removed, because it's in use");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("The state can't be removed, because it does not exist");
        }
    }
}
