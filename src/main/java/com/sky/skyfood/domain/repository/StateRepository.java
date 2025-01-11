package com.sky.skyfood.domain.repository;

import com.sky.skyfood.domain.entity.State;

import java.util.List;

public interface StateRepository {

    List<State> all();
    State getById(Long id);
    State add(State state);
    void remove(State state);

}
