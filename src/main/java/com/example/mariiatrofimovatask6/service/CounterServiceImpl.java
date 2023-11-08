package com.example.mariiatrofimovatask6.service;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.error.exceptions.NotFoundException;
import com.example.mariiatrofimovatask6.model.Counter;
import com.example.mariiatrofimovatask6.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mariiatrofimovatask6.mapper.CounterMapper.toCounter;
import static com.example.mariiatrofimovatask6.mapper.CounterMapper.toDTO;

@Service
@Slf4j
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService {
    private final CounterRepository repository;

    @Override
    public CounterDto add(CounterNewDto newDto) {
        Counter counter = toCounter(newDto);
        counter = repository.add(counter);
        return toDTO(counter);
    }

    @Override
    public CounterDto update(String name) {
        Counter counter = getOrNotFound(name);
        increaseCounter(counter);
        counter = repository.update(counter);
        return toDTO(counter);
    }

    @Override
    public CounterDto get(String name) {
        Counter counter = getOrNotFound(name);
        return toDTO(counter);
    }

    @Override
    public boolean delete(String name) {
        return repository.delete(name);
    }

    @Override
    public long getSum() {
        return repository.getSum();
    }

    @Override
    public List<String> getAllNames() {
        return repository.getAllNames();
    }

    private Counter getOrNotFound(String name) {
        return repository.get(name)
                .orElseThrow(() -> new NotFoundException(String.format("Counter with name %s not found.", name)));
    }

    private void increaseCounter(Counter counter) {
        long count = counter.getValue();
        count += 1;
        counter.setValue(count);
    }
}
