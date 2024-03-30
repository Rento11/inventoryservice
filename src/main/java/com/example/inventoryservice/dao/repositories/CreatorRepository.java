package com.example.inventoryservice.dao.repositories;

import com.example.inventoryservice.dao.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
}
