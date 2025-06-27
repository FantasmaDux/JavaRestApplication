package com.fantasmaDux.restApplication.repository;

import com.fantasmaDux.restApplication.entity.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorcycleRepo extends JpaRepository<Motorcycle, Long> {

}
