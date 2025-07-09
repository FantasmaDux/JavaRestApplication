package com.fantasmaDux.restApplication.controller;

import com.fantasmaDux.restApplication.entity.Motorcycle;
import com.fantasmaDux.restApplication.repository.MotorcycleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private MotorcycleRepo motorcycleRepo;

    @InjectMocks
    private MainController controller;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(controller, "objectMapper", new ObjectMapper());
    }

    @Test
    void changeMotorcycleFailedTest() {
        long id = 1;
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(id);

        when(motorcycleRepo.existsById(id)).thenReturn(false);

        String expected = "error";

        assertEquals(expected, controller.changeMotorcycle(motorcycle));
    }

    @Test
    void changeMotorcycleTest() {
        long id = 1;
        Motorcycle motorcycle = new Motorcycle();

        motorcycle.setId(id);
        motorcycle.setBrand("kawasaki");
        motorcycle.setModel("sf135");

        when(motorcycleRepo.existsById(id)).thenReturn(true);
        when(motorcycleRepo.save(motorcycle)).thenReturn(motorcycle);



        assertEquals(motorcycle.toString(), controller.changeMotorcycle(motorcycle));
    }

}