package com.fantasmaDux.restApplication.controller;

import com.fantasmaDux.restApplication.entity.Motorcycle;
import com.fantasmaDux.restApplication.repository.MotorcycleRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MotorcycleRepo motorcycleRepo;

    @PostMapping("/api/add")
    public void addMotorcycle(@RequestBody Motorcycle motorcycle) {
        try {
            String json = objectMapper.writeValueAsString(motorcycle);
            log.info("New row in table Motorcycle: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert motorcycle to JSON", e);
        }
        motorcycleRepo.save(motorcycle);
    }
}
