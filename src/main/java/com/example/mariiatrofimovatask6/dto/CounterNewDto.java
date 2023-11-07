package com.example.mariiatrofimovatask6.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Данные для добавления нового счетчика")
public class CounterNewDto {
    @NotBlank(message = "Name shouldn't be blank")
    @Size(min = 1, max = 128, message = "Names size should be from 1 to 128 characters")
    @Pattern(regexp = "\\S+", message = "Name shouldn't contain spaces")
    String name;
}
