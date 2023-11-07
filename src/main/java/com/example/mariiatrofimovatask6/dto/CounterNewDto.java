package com.example.mariiatrofimovatask6.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Getter
@Setter
@RequiredArgsConstructor
public class CounterNewDto {
    @NotBlank
    @Size(min = 1, max = 128)
    private String name;
}
