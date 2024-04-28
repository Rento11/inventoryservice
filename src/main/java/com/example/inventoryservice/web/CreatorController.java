package com.example.inventoryservice.web;

import com.example.inventoryservice.dao.entities.Creator;
import com.example.inventoryservice.service.CreatorManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CreatorController {

    @Autowired
    CreatorManager creatorManager;

    @GetMapping("/creatorsList")
    public String getCreators(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "taille", defaultValue = "1") int taille, @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Creator> creators = creatorManager.searchCreators(keyword, page, taille);
        model.addAttribute("creators", creators.getContent());
        int[] pages = new int[creators.getTotalPages()];
        for (int i = 0; i < pages.length; i++) {
            pages[i] = i;
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "creatorsList";
    }

    @GetMapping("/addCreator")
    public String addCreatorGet(Model model) {
        model.addAttribute("creator", new Creator());
        return "addCreator";
    }

    @PostMapping("/addCreator")
    public String addCreatorPost(Model model, @Valid Creator creator, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addCreator";
        }
        creatorManager.addCreator(creator);
        return "redirect:/creatorsList";
    }

    @GetMapping("/detailsCreator")
    public String getDetailsCreator(Model model, @RequestParam(name = "id") Integer id) {
        model.addAttribute("creator", creatorManager.findCreatorById(id));
        return "detailsCreator";
    }

    @GetMapping("/updateCreator")
    public String modifyCreator(Model model, @RequestParam(name = "id") Integer id) {
        Creator creator = creatorManager.findCreatorById(id);
        if (creator != null) {
            model.addAttribute("creator", creator);
            return "updateCreator";
        } else {
            return "error";
        }
    }

    @PostMapping("/updateCreator")
    public String modifyCreatorPost(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "name") String name, @RequestParam(name = "email") String email) {
        Creator creator = creatorManager.findCreatorById(id);
        creator.setName(name);
        creator.setEmail(email);
        if (creator != null) {
            creatorManager.updateCreator(creator);
            return "redirect:/creatorsList";
        } else {
            return "error";
        }
    }

    @GetMapping("/deleteCreator")
    public String deleteCreator(@RequestParam(name="id") Integer id, Integer page, String search){
        Creator creator = creatorManager.findCreatorById(id);
        if(creatorManager.deleteCreator(creator)){
            return "redirect:/creatorsList";
        }else {
            return "error";
        }
    }
}