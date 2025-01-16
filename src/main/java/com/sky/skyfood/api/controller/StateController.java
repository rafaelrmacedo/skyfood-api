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

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> getAllStates() {
        return stateRepository.all();
    }

    @GetMapping(value = "/{stateId}")
    public ResponseEntity<State> getById(@PathVariable Long stateId) {
        State state = stateRepository.getById(stateId);

        if (state != null) {
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State add(@RequestBody State state) {
        return stateService.add(state);
    }

    @PutMapping(value = "/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId, @RequestBody State state) {
        State currentyState = stateRepository.getById(stateId);

        if (currentyState != null) {
            BeanUtils.copyProperties(state, currentyState, "id");

            currentyState = stateService.add(currentyState);

            return ResponseEntity.ok(currentyState);
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            stateService.remove(stateId);
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
        State currentyState = stateRepository.getById(stateId);

        if (currentyState == null) {
            return ResponseEntity.notFound().build();
        }

        merge(fields, currentyState);

        return update(stateId, currentyState);
    }

    private void merge(Map<String, Object> fields, State currentyState) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(State.class, key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, currentyState, value);
        });
    }

}
