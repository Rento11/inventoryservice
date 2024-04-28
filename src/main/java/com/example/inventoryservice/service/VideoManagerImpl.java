package com.example.inventoryservice.service;

import com.example.inventoryservice.dao.entities.Video;
import com.example.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoManagerImpl implements VideoManager{
    @Autowired
    private VideoRepository videoRepository;
    @Override
    public Video addVideo(Video video) {
        try{
            return videoRepository.save(video);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Video updateVideo(Video video) {
        try{
            return videoRepository.save(video);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteVideo(Video video) {
        try{
            videoRepository.delete(video);
            return true;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public Video findVideo(Video video) {
        try{
            return videoRepository.findById(video.getId()).get();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Video findVideoById(int id) {
        try{
            return videoRepository.findById(id).get();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Video> getAllVideos() {
        try{
            return videoRepository.findAll();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public Page<Video> searchVideos(String keyword, int page, int taille){
        return videoRepository.findVideoByNameContainingIgnoreCase(keyword, PageRequest.of(page, taille));
    }
}
