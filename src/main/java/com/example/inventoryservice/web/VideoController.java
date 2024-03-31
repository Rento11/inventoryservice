package com.example.inventoryservice.web;

import com.example.inventoryservice.dao.entities.Creator;
import com.example.inventoryservice.dao.entities.Video;
import com.example.inventoryservice.service.VideoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoManager videoManager;

    @GetMapping("/videosList")
    public String getVideos(Model model) {
        List<Video> videos = videoManager.getAllVideos();
        model.addAttribute("videos", videos);
        return "videosList";
    }

    @GetMapping("/addVideo")
    public String addVideoGet(Model model) {
        return "addVideo";
    }

    @PostMapping("/addVideo")
    public String addVideoPost(Model model, @RequestParam(name = "name") String name, @RequestParam(name = "url") String url, @RequestParam(name = "description") String description, @RequestParam(name = "datePublication") Date datePublication) {
        Video video = new Video();
        video.setName(name);
        video.setUrl(url);
        video.setDescription(description);
        video.setDatePublication(datePublication);
        videoManager.addVideo(video);
        return getVideos(model);
    }

    @GetMapping("/detailsVideo")
    public String getDetailsVideo(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("video", videoManager.findVideoById(id));
        return "detailsVideo";
    }

    @GetMapping("/updateVideo")
    public String modifyVideo(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("video", videoManager.findVideoById(id));
        return "updateVideo";
    }

    @PostMapping("/updateVideo")
    public String modifyVideoPost(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name, @RequestParam(name = "url") String url, @RequestParam(name = "description") String description) {
        Video video = videoManager.findVideoById(id);
        video.setName(name);
        video.setUrl(url);
        video.setDescription(description);
        videoManager.updateVideo(video);
        return getVideos(model);
    }

    @GetMapping("/deleteVideo")
    public String deleteVideo(Model model, @RequestParam(name="id") Integer id){
        Video video = videoManager.findVideoById(id);
        video.setCreator(new Creator());
        videoManager.updateVideo(video);
        videoManager.deleteVideo(video);
        return getVideos(model);
    }
}