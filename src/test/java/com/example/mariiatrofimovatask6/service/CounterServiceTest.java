package com.example.mariiatrofimovatask6.service;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.error.exceptions.ConflictException;
import com.example.mariiatrofimovatask6.error.exceptions.NotFoundException;
import com.example.mariiatrofimovatask6.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CounterServiceTest {
    private final CounterService service;
    private final CounterRepository repository;

    private CounterNewDto newDto;
    private CounterDto dto;
    private CounterDto updatedDto;

    @BeforeEach
    void setUp() {
        newDto = CounterNewDto.builder().name("name").build();
        dto = CounterDto.builder().name("name").value(0).build();
        updatedDto = CounterDto.builder().name("name").value(1).build();
    }

    @AfterEach
    void tearDown() {
        repository.clear();
    }

    @Test
    void shouldAdd() {
        CounterDto result = service.add(newDto);
        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void shouldFailAdd() {
        service.add(newDto);
        String errorMessage = String.format("Counter with name %s exists", dto.getName());
        ConflictException exception = assertThrows(
                ConflictException.class,
                () -> service.add(newDto));
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldUpdate() {
        service.add(newDto);
        CounterDto result = service.update(dto.getName());
        assertNotNull(result);
        assertEquals(updatedDto, result);
    }

    @Test
    void shouldFailUpdate() {
        String errorMessage = String.format("Counter with name %s not found.", dto.getName());
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.update(dto.getName()));
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldGet() {
        service.add(newDto);
        CounterDto result = service.get(dto.getName());
        assertNotNull(result);
        assertEquals(dto, result);
    }

    @Test
    void shouldFailGet() {
        String errorMessage = String.format("Counter with name %s not found.", dto.getName());
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> service.get(dto.getName()));
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void shouldDelete() {
        service.add(newDto);
        boolean result = service.delete(dto.getName());
        assertTrue(result);
    }

    @Test
    void shouldFailDelete() {
        boolean result = service.delete(dto.getName());
        assertFalse(result);
    }

    @Test
    void shouldGetSum() {
        //empty
        long result = service.getSum();
        assertEquals(0, result);

        //single
        service.add(newDto);
        service.update(dto.getName());
        result = service.getSum();
        assertEquals(1, result);

        //multiple
        CounterNewDto newDto2 = CounterNewDto.builder().name("name2").build();
        service.add(newDto2);
        service.update(newDto2.getName());
        assertEquals(2, service.getSum());
    }

    @Test
    void shouldGetAllNames() {
        //empty
        List<String> result = service.getAllNames();
        assertNotNull(result);
        assertEquals(0, result.size());

        //single
        service.add(newDto);
        service.update(dto.getName());
        result = service.getAllNames();
        assertNotNull(result);
        assertEquals(1, result.size());

        //multiple
        CounterNewDto newDto2 = CounterNewDto.builder().name("name2").build();
        service.add(newDto2);
        service.update(newDto2.getName());
        result = service.getAllNames();
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}