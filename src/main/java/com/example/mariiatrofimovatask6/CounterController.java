package com.example.mariiatrofimovatask6;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.service.CounterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/counters")
@RequiredArgsConstructor
@Tag(name = "Public: счётчики", description = "Публичный API для работы с счётчиками")
@Slf4j
public class CounterController {
    private final CounterService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добавление счетчика",
            description = "Название счетчика должно быть уникальным"
    )
    public CounterDto add(@Valid @RequestBody CounterNewDto newDto) {
        return service.add(newDto);
    }

    @PatchMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Инкремент значения счетчика с указанным именем")
    public CounterDto update(@PathVariable String name) {
        return service.update(name);
    }

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение значения счетчика с указанным именем")
    public CounterDto get(@PathVariable String name) {
        return service.get(name);
    }

    @DeleteMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление счетчика с указанным именем")
    public boolean delete(@PathVariable String name) {
        return service.delete(name);
    }

    @GetMapping("/sum")
    @Operation(summary = "Получить суммарное значение всех счетчиков")
    public long getSum() {
        return service.getSum();
    }

    @GetMapping(value = "/names", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Получить уникальные имена счетчиков в виде списка")
    public List<String> getAllNames() {
        return service.getAllNames();
    }
}
