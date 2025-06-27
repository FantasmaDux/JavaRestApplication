package com.fantasmaDux.restApplication.controller;

import com.fantasmaDux.restApplication.entity.Motorcycle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/api/main")
    public String mainListener() {
        return "Hello world!";
    }

    @GetMapping("/api/motorcycle")
    public String giveMotorcycle() {
        Motorcycle motorcycle = new Motorcycle("Honda", "CBR600RR", 2015);
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(motorcycle);
        } catch (JsonProcessingException e) {
            System.out.println("Error with motorcyle");
        }
        return jsonData;
    }

    @PostMapping("/api/model")
    public String postMotorcycleModel(@RequestParam String model) {
        Motorcycle motorcycle = new Motorcycle("Honda", model, 2015);
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(motorcycle);
        } catch (JsonProcessingException e) {
            System.out.println("Error with motorcyle");
        }
        return jsonData;
    }

}
