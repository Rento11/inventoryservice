package com.example.inventoryservice.dao.repositories;

import com.example.inventoryservice.dao.entities.Creator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
    Page<Creator> findCreatorByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
