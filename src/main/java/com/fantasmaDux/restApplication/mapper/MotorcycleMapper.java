package com.fantasmaDux.restApplication.mapper;

import com.fantasmaDux.restApplication.dto.MotorcycleDTO;
import com.fantasmaDux.restApplication.entity.Motorcycle;

public class MotorcycleMapper {
    public static Motorcycle toEntity(MotorcycleDTO motorcycleDTO) {
        return new Motorcycle(motorcycleDTO.getBrand(),
                motorcycleDTO.getModel(), motorcycleDTO.getYear());
    }
    public static MotorcycleDTO toDTO(Motorcycle entity) {
        MotorcycleDTO motorcycleDTO = new MotorcycleDTO();
        motorcycleDTO.setBrand(entity.getBrand());
        motorcycleDTO.setModel(entity.getModel());
        motorcycleDTO.setYear(entity.getYear());
        return motorcycleDTO;
    }
}
