package com.example.mariiatrofimovatask6.repository;

import com.example.mariiatrofimovatask6.model.Counter;

import java.util.List;
import java.util.Optional;

public interface CounterRepository {
    Counter add(Counter counter);

    Counter update(Counter counter);

    Optional<Counter> get(String name);

    boolean delete(String name);

    long getSum();

    List<String> getAllNames();
}
