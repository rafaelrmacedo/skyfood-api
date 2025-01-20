package com.sky.skyfood.api.controller;

import com.sky.skyfood.domain.entity.State;
import com.sky.skyfood.domain.exception.EntityInUseException;
import com.sky.skyfood.domain.exception.EntityNotFoundException;
import com.sky.skyfood.domain.repository.StateRepository;
import com.sky.skyfood.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @GetMapping(value = "/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId) {
        Optional<State> state = stateRepository.findById(stateId);

        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping(value = "/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
        Optional<State> currentyState = stateRepository.findById(stateId);

        if (currentyState.isPresent()) {
            BeanUtils.copyProperties(state, currentyState, "id");

            State savedState = stateService.save(currentyState.get());

            return ResponseEntity.ok(savedState);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            stateService.deleteById(stateId);
            return ResponseEntity.ok().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{stateId}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long stateId,
                                           @RequestBody Map<String, Object> fields) {
        Optional<State> currentyState = stateRepository.findById(stateId);

        if (currentyState.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentyState.get());

        return update(stateId, currentyState.get());
    }

    private void merge(Map<String, Object> fields, State currentyState) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(State.class, key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, currentyState, value);
        });
    }

}
