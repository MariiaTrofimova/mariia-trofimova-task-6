package com.example.mariiatrofimovatask6;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/counters")
@RequiredArgsConstructor
@Slf4j
public class CounterController {
    private final CounterService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CounterDto add(@Valid @RequestBody CounterNewDto newDto) {
        return service.add(newDto);
    }

    @PatchMapping("/{name}")
    public CounterDto update(@PathVariable String name) {
        return service.update(name);
    }

    @GetMapping("/{name}")
    public CounterDto get(@PathVariable String name) {
        return service.get(name);
    }

    @DeleteMapping("/{name}")
    public boolean delete(@PathVariable String name) {
        return service.delete(name);
    }

    @GetMapping("/sum")
    public long getSum() {
        return service.getSum();
    }

    @GetMapping("/list")
    public List<String> getAllNames() {
        return service.getAllNames();
    }
}
