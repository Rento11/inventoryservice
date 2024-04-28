package com.example.inventoryservice.web;

import com.example.inventoryservice.dao.entities.Creator;
import com.example.inventoryservice.dao.entities.Video;
import com.example.inventoryservice.service.VideoManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoManager videoManager;

    @GetMapping("/videosList")
    public String getVideos(Model model,@RequestParam(name = "search", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "1") int taille) {
        Page<Video> videos = videoManager.searchVideos(keyword, page, taille);
        model.addAttribute("videos", videos);
        int[] pages = new int[videos.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "videosList";
    }

    @GetMapping("/addVideo")
    public String addVideoGet(Model model) {
        model.addAttribute("video", new Video());
        return "addVideo";
    }

    @PostMapping("/addVideo")
    public String addVideoPost(@Valid Video video, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addVideo";
        }
        videoManager.addVideo(video);
        return "redirect:/videosList";
    }

    @GetMapping("/detailsVideo")
    public String getDetailsVideo(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("video", videoManager.findVideoById(id));
        return "detailsVideo";
    }

    @GetMapping("/updateVideo")
    public String modifyVideo(Model model, @RequestParam(name = "id") Integer id) {
        Video video = videoManager.findVideoById(id);
        if (video != null) {
            model.addAttribute("video", video);
            return "updateVideo";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateVideo")
    public String modifyVideoPost(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name, @RequestParam(name = "url") String url, @RequestParam(name = "description") String description) {
        Video video = videoManager.findVideoById(id);
        video.setName(name);
        video.setUrl(url);
        video.setDescription(description);
        if (video != null) {
            videoManager.updateVideo(video);
            return "redirect:/videosList";
        } else {
            return "error";
        }

    }

    @GetMapping("/deleteVideo")
    public String deleteVideo(@RequestParam(name="id") Integer id){
        if(videoManager.deleteVideo(videoManager.findVideoById(id))) {
            return "redirect:/videosList";
        }
        else{
            return "error";
        }
    }
}