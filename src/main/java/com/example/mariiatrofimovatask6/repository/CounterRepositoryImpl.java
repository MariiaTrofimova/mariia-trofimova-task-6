package com.example.mariiatrofimovatask6.repository;

import com.example.mariiatrofimovatask6.error.exceptions.ConflictException;
import com.example.mariiatrofimovatask6.model.Counter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class CounterRepositoryImpl implements CounterRepository {
    private final Map<String, Counter> counters = new HashMap<>();


    @Override
    public Counter add(Counter counter) {
        String name = counter.getName();
        if (counters.containsKey(name)) {
            log.warn("Counter with name {} exists", name);
            throw new ConflictException(String.format("Counter with name %s exists", name));
        }
        counters.put(name, counter);
        log.info("Counter with name {} was saved", name);
        return counter;
    }

    @Override
    public Counter update(Counter counter) {
        String name = counter.getName();
        counters.put(name, counter);
        log.info("Counter with name {} was updated", name);
        return counter;
    }

    @Override
    public Optional<Counter> get(String name) {
        return Optional.of(counters.get(name));
    }

    @Override
    public boolean delete(String name) {
        return counters.remove(name) != null;
    }

    @Override
    public long getSum() {
        return counters.values().stream().mapToLong(Counter::getCount).sum();
    }

    @Override
    public List<String> getAllNames() {
        return new ArrayList<>(counters.keySet());
    }
}
