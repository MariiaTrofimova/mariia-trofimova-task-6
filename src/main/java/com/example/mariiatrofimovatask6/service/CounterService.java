package com.example.mariiatrofimovatask6.service;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;

import java.util.List;

public interface CounterService {
    CounterDto add(CounterNewDto newDto);

    CounterDto update(String name);

    CounterDto get(String name);

    boolean delete(String name);

    long getSum();

    List<String> getAllNames();
}
