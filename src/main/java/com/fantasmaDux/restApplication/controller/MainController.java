package com.fantasmaDux.restApplication.controller;

import com.fantasmaDux.restApplication.dto.MotorcycleDTO;
import com.fantasmaDux.restApplication.entity.Motorcycle;
import com.fantasmaDux.restApplication.mapper.MotorcycleMapper;
import com.fantasmaDux.restApplication.repository.MotorcycleRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Motorcycle_Methods")
@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MotorcycleRepo motorcycleRepo;

    @Operation(
            summary = "Put new motorcycle in data base", // краткое описание метода
            description = "Get DTO of motorcycle and with mapper recreate it " +
                    "to entity. Then save in db."

    )
    @PostMapping("/add")
    public Motorcycle addMotorcycle(@RequestBody MotorcycleDTO motorcycleDTO) {
        Motorcycle motorcycle = MotorcycleMapper.toEntity(motorcycleDTO);

        return motorcycleRepo.save(motorcycle);
//        Motorcycle motorcycle = MotorcycleMapper.toEntity(motorcycleDTO);
//
//        try {
//            String json = objectMapper.writeValueAsString(motorcycle);
//            log.info("New row in table Motorcycle: {}", json);
//        } catch (JsonProcessingException e) {
//            log.error("Failed to convert motorcycle to JSON");
//        }
//        motorcycleRepo.save(motorcycle);
    }

    @GetMapping("/all")
    public String getAllMotorcycle() {
        List<Motorcycle> motorcycles = motorcycleRepo.findAll();
        try {
            return objectMapper.writeValueAsString(motorcycles);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("")
    public Motorcycle getMotorcycle(@RequestParam long id) {
        return motorcycleRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("")
    public void deleteMotorcycle(@RequestParam long id) {
        if (motorcycleRepo.existsById(id)) {
            motorcycleRepo.deleteById(id);
            log.info("row with id {} in table was deleted", id);
        } else {
            log.warn("Attempted to delete non-existing motorcycle with ID {}", id);
        }
    }

    @PutMapping("")
    public String changeMotorcycle(@RequestBody Motorcycle motorcycle) {
        if (!motorcycleRepo.existsById(motorcycle.getId())) {
            log.error("No such object (row)");
            return "error";
        }
        try {
            String json = objectMapper.writeValueAsString(motorcycle);
            log.info("New row info for table Motorcycle: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert motorcycle to JSON");
        }
        return motorcycleRepo.save(motorcycle).toString();
    }
}
