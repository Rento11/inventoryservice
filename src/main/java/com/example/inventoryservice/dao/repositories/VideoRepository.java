package com.example.inventoryservice.dao.repositories;

import com.example.inventoryservice.dao.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
    Page<Video> findVideoByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
