package com.example.inventoryservice.service;

import com.example.inventoryservice.dao.entities.Video;

import java.util.List;

public interface VideoManager {
    public Video addVideo(Video video);
    public Video updateVideo(Video video);
    public boolean deleteVideo(Video video);
    public Video findVideo(Video video);
    public Video findVideoById(int id);
    public List<Video> getAllVideos();
}
