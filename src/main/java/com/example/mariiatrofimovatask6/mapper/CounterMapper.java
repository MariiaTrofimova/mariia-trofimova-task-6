package com.example.mariiatrofimovatask6.mapper;


import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.model.Counter;

public class CounterMapper {

    public static CounterDto toDTO(Counter counter) {
        return CounterDto.builder()
                .name(counter.getName())
                .count(counter.getCount())
                .build();
    }

    public static Counter toCounter(CounterNewDto dto) {
        return Counter.builder()
                .name(dto.getName())
                .count(0L)
                .build();
    }
}
