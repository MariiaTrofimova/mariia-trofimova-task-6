package com.example.mariiatrofimovatask6;

import com.example.mariiatrofimovatask6.dto.CounterDto;
import com.example.mariiatrofimovatask6.dto.CounterNewDto;
import com.example.mariiatrofimovatask6.error.exceptions.NotFoundException;
import com.example.mariiatrofimovatask6.service.CounterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CounterController.class)
@AutoConfigureMockMvc
class CounterControllerTest {
    private static final String URL = "/counters";

    @InjectMocks
    private CounterController controller;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mvc;

    @MockBean
    private CounterService service;

    private ObjectMapper mapper = new ObjectMapper();
    private CounterNewDto newDto;
    private CounterDto dto;

    @BeforeEach
    void setUp() {
        newDto = CounterNewDto.builder()
                .name("name")
                .build();
        dto = CounterDto.builder()
                .name("name")
                .value(0)
                .build();
    }

    @Test
    public void shouldCreateMockMvc() {
        assertNotNull(mvc);
    }

    @Test
    void shouldAdd() throws Exception {
        String json = mapper.writeValueAsString(newDto);
        String expected = mapper.writeValueAsString(dto);
        when(service.add(any())).thenReturn(dto);
        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expected));
    }

    @Test
    void shouldFailAdd() throws Exception {
        //have space in name
        CounterNewDto badNewDto = CounterNewDto.builder()
                .name("na me").build();
        String json = mapper.writeValueAsString(badNewDto);
        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        //empty name
        badNewDto.setName("");
        json = mapper.writeValueAsString(badNewDto);
        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());

        //to long name
        String longName = "a";
        badNewDto.setName(longName.repeat(129));
        json = mapper.writeValueAsString(badNewDto);
        mvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdate() throws Exception {
        CounterDto updatedDto = CounterDto.builder()
                .name("name")
                .value(1)
                .build();
        when(service.update(any())).thenReturn(updatedDto);
        String json = mapper.writeValueAsString(dto);
        String expected = mapper.writeValueAsString(updatedDto);
        mvc.perform(patch(URL + "/name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void shouldFailUpdate() throws Exception {
        when(service.update(any())).thenThrow(new NotFoundException("Not found"));
        mvc.perform(patch(URL + "/name"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGet() throws Exception {
        when(service.get(any())).thenReturn(dto);
        String json = mapper.writeValueAsString(dto);
        mvc.perform(get(URL + "/name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void shouldFailGet() throws Exception {
        when(service.get(any())).thenThrow(new NotFoundException("Not found"));
        mvc.perform(get(URL + "/name"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDelete() throws Exception {
        when(service.delete(any())).thenReturn(true);
        mvc.perform(delete(URL + "/name"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetSum() throws Exception {
        //empty List
        mvc.perform(get(URL + "/sum"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("0"));

        //single list
        when(service.getSum()).thenReturn(1L);
        mvc.perform(get(URL + "/sum"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("1"));
    }

    @Test
    void shouldGetAllNames() throws Exception {
        //empty List
        when(service.getAllNames()).thenReturn(Collections.emptyList());
        mvc.perform(get(URL + "/names"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        //single list
        List<String> names = new ArrayList<>();
        names.add("name");
        String json = mapper.writeValueAsString(names);
        when(service.getAllNames()).thenReturn(names);
        mvc.perform(get(URL + "/names"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));

        //multiple list
        names.add("name1");
        json = mapper.writeValueAsString(names);
        when(service.getAllNames()).thenReturn(names);
        mvc.perform(get(URL + "/names"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(json));

        verify(service, atLeast(1)).getAllNames();
    }
}