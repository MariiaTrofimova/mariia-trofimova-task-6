package com.example.mariiatrofimovatask6.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CounterDto {
    private String name;
    private long count;
}
